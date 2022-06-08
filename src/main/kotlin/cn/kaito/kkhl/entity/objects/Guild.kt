package cn.kaito.kkhl.entity.objects

import cn.kaito.kkhl.entity.enums.NotifyType

data class Guild(
    val id: String,
    val name: String,
    val user_id: String,
    val icon: String,
    val notify_type: NotifyType,
    val region: String,
    val enable_open: Boolean,
    val open_id: Long,
    val default_channel_id: String,
    val welcome_channel_id: String
)