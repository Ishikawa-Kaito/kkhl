package cn.kaito.kkhl.network

import cn.kaito.kkhl.API_BASE
import cn.kaito.kkhl.entity.enums.MessageType
import cn.kaito.kkhl.entity.enums.MessageType.Text
import cn.kaito.kkhl.event.RawEvent
import cn.kaito.kkhl.event.base.EventExtraBase
import cn.kaito.kkhl.network.base.IReceiver
import cn.kaito.kkhl.utils.BooleanTypeAdapter
import cn.kaito.kkhl.utils.zlibDecompress
import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.websocket.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.reflections.Reflections
import org.reflections.util.ConfigurationBuilder
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.reflect.full.createInstance

class WebsocketReceiver(private val token: String) : IReceiver {

    @Volatile
    private var sn: Int = 0

    override val name: String
        get() = "Websocket"

    override val dataBuffer: ConcurrentLinkedQueue<EventExtraBase> = ConcurrentLinkedQueue()

    private val eventTypes = Reflections(ConfigurationBuilder().forPackage("cn.kaito.kkhl.event"))
        .getSubTypesOf(EventExtraBase::class.java)
        .map { it.kotlin }

    private val client = HttpClient {
        install(Logging)
        install(WebSockets)
        install(ContentNegotiation) {
            gson()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }
    }

    override suspend fun connect() {
        val gateWay = getGateway()
        client.webSocket(gateWay) {
            val data = parse(incoming.receive())
            if (data.s != 1) {
                println("Error while connecting")
                cancel()
            }
            val receiveRoutine = launch { frameIn() }
            val pingRoutine = launch { heartBeat() }

            receiveRoutine.join()
            pingRoutine.cancelAndJoin()
        }
    }

    private fun reconnect() {
        TODO()
    }

    private suspend fun DefaultClientWebSocketSession.frameIn() {
        try {
            for (frame in incoming) {
                dispatch(frame)
            }
        } catch (e: Exception) {
            println("Error while receiving: " + e.localizedMessage)
            reconnect()
        }
    }

    private suspend fun DefaultClientWebSocketSession.heartBeat() {
        try {
            while (true) {
                send(makeHeartBeatPkt(sn))
                println("Send PING.")
                delay(26 * 1000)
            }
        } catch (e: Exception) {
            println("Error while PING: " + e.localizedMessage)
            reconnect()
        }
    }

    private fun makeHeartBeatPkt(sn: Int): String {
        return "{\"s\":2,\"sn\":$sn}"
    }

    private fun dispatch(frame: Frame) {
        val data = parse(frame)
        when (data.s) {
            0 -> {
                eventParse(data)
                println(dataBuffer.poll().body)
            }
            3 -> {
                println("receive PONG.")
            }
            5 -> {
                println("connection expired")
                reconnect()
            }
        }

    }

    private fun eventParse(frame: WSFrame) {
        val rawEvent = gson.fromJson(gson.toJson(frame.d), RawEvent::class.java)
        val type = rawEvent.extra.type
        val dstEventType = eventTypes.find {
            if (it.createInstance().type is Int) {
                if (type !is Double) false
                else
                    it.createInstance().type == type.toString().toDouble().toInt()
            } else {
                it.createInstance().type == type
            }
        } ?: return
        val dstEvent = gson.fromJson(gson.toJson(rawEvent.extra), dstEventType.java)
        this.dataBuffer.offer(dstEvent)
        this.sn = frame.sn ?: this.sn
    }

    private val gson = GsonBuilder().also {
        it.registerTypeAdapter(MessageType::class.java, Text)
        it.registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
    }.create()

    private fun parse(frame: Frame) = gson.fromJson(frame.data.zlibDecompress(), WSFrame::class.java)

    private suspend fun getGateway(): String {
        val response = http("$API_BASE/gateway/index")
        if (response.code != 0) {
            return ""
        }
        return response.data["url"]!!
    }

    private suspend fun http(url: String) = this.client.get(url) {
        header(HttpHeaders.Authorization, "Bot $token")
    }.body<HttpResponse>()

}
