package cn.kaito.kkhl.event

import cn.kaito.kkhl.entity.objects.User
import cn.kaito.kkhl.event.base.EventExtraBase

class UserJoinedChannelEvent : EventExtraBase {
    override val type: Any
        get() = "joined_channel"

    override lateinit var body: UserJoinedChannelEventBody
}

data class UserJoinedChannelEventBody(
    val user_id: String,
    val channel_id: String,
    val joined_at: Long
)

class UserExitedChannelEvent : EventExtraBase {
    override val type: Any
        get() = "exited_channel"

    override lateinit var body: UserExitedChannelEventBody
}

data class UserExitedChannelEventBody(
    val user_id: String,
    val channel_id: String,
    val exited_at: Long
)

class UserUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "user_updated"

    override lateinit var body: UserUpdatedEventBody
}

data class UserUpdatedEventBody(
    val user_id: String,
    val username: String,
    val avatar: String
)

class BotJoinedGuildEvent : EventExtraBase {
    override val type: Any
        get() = "self_joined_guild"

    override lateinit var body: BotGuildEventBody
}

class BotExitedGuildEvent : EventExtraBase {
    override val type: Any
        get() = "self_exited_guild"

    override lateinit var body: BotGuildEventBody
}

data class BotGuildEventBody(
    val guild_id: String
)

class MessageButtonClickedEvent : EventExtraBase {
    override val type: Any
        get() = "message_btn_click"

    override lateinit var body: MessageButtonClickedEventBody
}

data class MessageButtonClickedEventBody(
    val msg_id: String,
    val user_id: String,
    val value: String,
    val target_id: String,
    val user_info: User
)

