package cn.kaito.kkhl.entity.objects

data class Channel(
    val guild_id: String,
    val id: String,
    val is_category: Boolean,
    val level: Int,
    val name: String,
    val parent_id: String,
    val permission_overwrites: List<PermissionOverwrite>,
    val permission_sync: Int,
    val permission_users: List<PermissionUser>,
    val slow_mode: Int,
    val topic: String,
    val type: Int,
    val user_id: String
)

data class PermissionOverwrite(
    val allow: Int,
    val deny: Int,
    val role_id: Int
)

data class PermissionUser(
    val allow: Int,
    val deny: Int,
    val user: User
)

