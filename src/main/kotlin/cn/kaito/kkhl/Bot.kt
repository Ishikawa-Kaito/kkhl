package cn.kaito.kkhl

import cn.kaito.kkhl.network.WebsocketReceiver
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlin.coroutines.CoroutineContext

class Bot(token: String) : CoroutineScope {

    private val receiver = WebsocketReceiver(token)

    suspend fun initBot() {
        receiver.connect()
        joinAll()
    }

    override val coroutineContext: CoroutineContext = CoroutineName("KKHL")

}
