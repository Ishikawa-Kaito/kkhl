package cn.kaito.kkhl.network.base

import cn.kaito.kkhl.event.base.EventExtraBase
import java.util.concurrent.ConcurrentLinkedQueue

interface IReceiver {

    // 接收器类型
    val name: String

    // 接收数据暂存队列
    val dataBuffer: ConcurrentLinkedQueue<EventExtraBase>

    // 启动receiver
    suspend fun connect()

}