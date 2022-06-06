package cn.kaito.kkhl.entity


data class User(
    val id: String,
    val username: String,
    val identify_num: String,
    val online: Boolean,
    val avatar: String,
    val vip_avatar: String,
    val bot: Boolean,
    val status: Int,
    val mobile_verified: Boolean,
    val nickname: String,
    val roles: List<Int>,
)