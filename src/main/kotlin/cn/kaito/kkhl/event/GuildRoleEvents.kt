package cn.kaito.kkhl.event

import cn.kaito.kkhl.entity.objects.Role
import cn.kaito.kkhl.event.base.EventExtraBase

class GuildRoleAddedEvent : EventExtraBase {
    override val type: Any
        get() = "added_role"

    override lateinit var body: Role
}

class GuildRoleDeletedEvent : EventExtraBase {
    override val type: Any
        get() = "deleted_role"

    override lateinit var body: Role
}

class GuildRoleUpdatedEvent : EventExtraBase {
    override val type: Any
        get() = "updated_role"

    override lateinit var body: Role
}
