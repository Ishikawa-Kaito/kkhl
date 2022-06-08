package cn.kaito.kkhl.entity.messages

import cn.kaito.kkhl.entity.objects.User

data class TextMessage(
    val type: Int,
    val guild_id: String,
    val channel_name: String,
    val mention: List<String>,
    val mention_all: Boolean,
    val mention_roles: List<String>,
    val mention_here: Boolean,
    val author: User
)

