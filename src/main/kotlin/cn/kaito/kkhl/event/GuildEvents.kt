package cn.kaito.kkhl.event

import cn.kaito.kkhl.entity.objects.Guild
import cn.kaito.kkhl.event.base.EventExtraBase

class GuildUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "updated_guild"

    override lateinit var body: Guild
}

class GuildDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_guild"

    override lateinit var body: Guild
}

class GuildUserBannedEvent : EventExtraBase {
    override val type: Any
        get() = "added_block_list"

    override lateinit var body: GuildUserBannedEventBody
}

data class GuildUserBannedEventBody(
    val operator_id: String,
    val remark: String,
    val user_id: List<String>
)


class GuildUserUnBannedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_block_list"

    override lateinit var body: GuildUserUnBannedEventBody
}

data class GuildUserUnBannedEventBody(
    val operator_id: String,
    val user_id: List<String>
)




