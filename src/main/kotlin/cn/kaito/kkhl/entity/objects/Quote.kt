package cn.kaito.kkhl.entity.objects

data class Quote(
    val author: Author,
    val content: String,
    val create_at: Long,
    val id: String,
    val type: Int
)

data class Author(
    val active_time: Long,
    val avatar: String,
    val bot: Boolean,
    val id: String,
    val identify_num: String,
    val is_vip: Boolean,
    val joined_at: Long,
    val mobile_verified: Boolean,
    val nickname: String,
    val online: Boolean,
    val os: String,
    val roles: List<Int>,
    val status: Int,
    val username: String,
    val vip_avatar: String
)

data class Attachment(
    val name: String,
    val size: Int,
    val type: String,
    val url: String
)