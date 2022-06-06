package cn.kaito.kkhl.entity

data class Guild(
    val channels: List<Channel>,
    val default_channel_id: String,
    val enable_open: Boolean,
    val icon: String,
    val id: String,
    val master_id: String,
    val name: String,
    val notify_type: Int,
    val open_id: String,
    val region: String,
    val roles: List<Role>,
    val topic: String,
    val welcome_channel_id: String
)