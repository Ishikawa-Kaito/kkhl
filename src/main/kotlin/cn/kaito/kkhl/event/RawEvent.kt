package cn.kaito.kkhl.event

import cn.kaito.kkhl.entity.enums.ChannelType
import cn.kaito.kkhl.entity.enums.MessageType
import cn.kaito.kkhl.event.base.EventExtraBase


class RawEvent(
    val channel_type: ChannelType,
    val type: MessageType,
    val target_id: String,
    val author_id: String,
    val content: String,
    val msg_id: String,
    val msg_timestamp: Long,
    val nonce: String,
    val extra: RawEventExtra
)

class RawEventExtra : EventExtraBase {
    override var type: Any = ""
    override var body: Map<String, Any> = mapOf()
}