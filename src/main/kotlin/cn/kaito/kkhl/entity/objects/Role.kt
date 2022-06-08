package cn.kaito.kkhl.entity.objects


data class Role(
    val color: Int,
    val hoist: Int,
    val mentionable: Int,
    val name: String,
    val permissions: Int,
    val position: Int,
    val role_id: Int
)