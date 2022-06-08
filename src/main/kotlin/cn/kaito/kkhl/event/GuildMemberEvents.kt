package cn.kaito.kkhl.event

import cn.kaito.kkhl.event.base.EventExtraBase

class GuildMemberJoinedEvent : EventExtraBase {
    override val type: Any
        get() = "joined_guild"

    override lateinit var body: GuildMemberJoinedEventBody
}

data class GuildMemberJoinedEventBody(
    val user_id: String,
    val joined_at: Long
)

class GuildMemberExitedEvent : EventExtraBase {
    override val type: Any
        get() = "exited_guild"

    override lateinit var body: GuildMemberExitedEventBody
}

data class GuildMemberExitedEventBody(
    val user_id: String,
    val exited_at: Long
)

class GuildMemberUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "updated_guild_member"

    override lateinit var body: GuildMemberUpdatedEventBody
}

data class GuildMemberUpdatedEventBody(
    val user_id: String,
    val nickname: String
)

class GuildMemberOnlineEvent : EventExtraBase {
    override val type: Any
        get() = "guild_member_online"

    override lateinit var body: GuildMemberStatusEventBody
}

class GuildMemberOfflineEvent : EventExtraBase {
    override val type: Any
        get() = "guild_member_offline"

    override lateinit var body: GuildMemberStatusEventBody
}


data class GuildMemberStatusEventBody(
    val user_id: String,
    val event_time: Long,
    val guilds: List<String>
)


