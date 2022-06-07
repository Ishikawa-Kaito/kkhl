package cn.kaito.kkhl.event

import cn.kaito.kkhl.event.base.ChannelType
import cn.kaito.kkhl.event.base.EventExtraBase
import cn.kaito.kkhl.event.base.MessageType


data class RawEvent(
    val channel_type: ChannelType,
    val type: MessageType,
    val target_id: String,
    val author_id: String,
    val content: String,
    val msg_id: String,
    val msg_timestamp: Long,
    val nonce: String,
    val extra: RawEventExtra,
)


data class RawEventExtra(override val type: String, override val body: Map<String,Any>):EventExtraBase