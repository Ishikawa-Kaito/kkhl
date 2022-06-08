package cn.kaito.kkhl.entity.messages

import cn.kaito.kkhl.entity.objects.User

data class KMarkdownMessage(
    val type: Int,
    val guild_id: String,
    val channel_name: String,
    val mention: List<String>,
    val mention_all: Boolean,
    val mention_roles: List<String>,
    val mention_here: Boolean,
    val author: User,
    val nav_channel: List<String>,
    val code: String,
    val kmarkdown: KMarkdown
)

data class KMarkdown(
    val raw_content: String,
    val mention_part: List<String>,
    val mention_role_part: List<String>,
)