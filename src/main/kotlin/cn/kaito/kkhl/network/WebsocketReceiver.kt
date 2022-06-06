package cn.kaito.kkhl.network

import cn.kaito.kkhl.API_BASE
import cn.kaito.kkhl.network.base.IReceiver
import cn.kaito.kkhl.utils.zlibDecompress
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentLinkedQueue

class WebsocketReceiver(private val token: String) : IReceiver {

    @Volatile
    private var sn: Int = 0

    override val name: String
        get() = "Websocket"
    override val dataBuffer: ConcurrentLinkedQueue<String> = ConcurrentLinkedQueue()

    private val client = HttpClient {
        install(Logging)
        install(WebSockets)
        install(ContentNegotiation) {
            json()
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
            val inRoutine = launch { frameIn() }
            val pingRoutine = launch { heartBeat() }

            inRoutine.join()
            pingRoutine.cancelAndJoin()
        }
    }

    private fun reconnect() {

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

    fun dispatch(frame: Frame) {
        val data = parse(frame)
        when (data.s) {
            0 -> {
                println(frame.data.zlibDecompress())
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

    private val json = Json { ignoreUnknownKeys = true }

    private fun parse(frame: Frame) = json.decodeFromString<WSFrame>(frame.data.zlibDecompress())

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
