package cn.kaito.kkhl.event

import cn.kaito.kkhl.event.base.EventExtraBase

class PrivateMessageUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "updated_private_message"

    override lateinit var body: PrivateMessageUpdatedEventBody
}

data class PrivateMessageUpdatedEventBody(
    val msg_id: String,
    val author_id: String,
    val target_id: String,
    val content: String,
    val chat_code: String,
    val updated_at: Long
)

class PrivateMessageDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_private_message"

    override lateinit var body: PrivateMessageDeletedEventBody
}

data class PrivateMessageDeletedEventBody(
    val msg_id: String,
    val author_id: String,
    val target_id: String,
    val content: String,
    val chat_code: String,
    val deleted_at: Long
)


class PrivateReactionAddedEvent : EventExtraBase {
    override val type: Any
        get() = "private_added_reaction"

    override lateinit var body: PrivateReactionEventBody
}

class PrivateReactionDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "private_added_reaction"

    override lateinit var body: PrivateReactionEventBody
}

data class PrivateReactionEventBody(
    val msg_id: String,
    val user_id: String,
    val chat_code: String,
    val emoji: Map<String, String>
)



