package cn.kaito.kkhl.event

import cn.kaito.kkhl.entity.objects.Channel
import cn.kaito.kkhl.event.base.EventExtraBase

class ReactionAddedEvent : EventExtraBase {
    override val type: Any
        get() = "added_reaction"
    override lateinit var body: ChannelReactionEventBody
}

class ReactionDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_reaction"
    override lateinit var body: ChannelReactionEventBody
}

data class ChannelReactionEventBody(
    val msg_id: String,
    val user_id: String,
    val channel_id: String,
    val emoji: Map<String, String>
)

class MessageUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "updated_message"
    override lateinit var body: MessageUpdatedEventBody
}

class MessageDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_message"
    override lateinit var body: MessageDeletedEventBody
}

data class MessageUpdatedEventBody(
    val msg_id: String,
    val content: String,
    val channel_id: String,
    val mention: List<String>,
    val mention_all: Boolean,
    val mention_here: Boolean,
    val mention_roles: List<String>,
    val update_at: Long
)

data class MessageDeletedEventBody(
    val msg_id: String,
    val channel_id: String
)

class ChannelAddedEvent : EventExtraBase {
    override val type: Any
        get() = "added_channel"

    override lateinit var body: Channel
}

class ChannelUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "updated_channel"

    override lateinit var body: Channel
}

class ChannelDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_channel"

    override lateinit var body: ChannelDeletedEventBody
}

data class ChannelDeletedEventBody(
    val id: String,
    val deleted_at: Long
)

class ChannelMessagePinnedEvent : EventExtraBase {
    override val type: Any
        get() = "pinned_message"

    override lateinit var body: ChannelMessagePinEventBody
}

class ChannelMessageUnpinnedEvent : EventExtraBase {
    override val type: Any
        get() = "unpinned_message"

    override lateinit var body: ChannelMessagePinEventBody
}

data class ChannelMessagePinEventBody(
    val channel_id: String,
    val operator_id: String,
    val msg_id: String
)


