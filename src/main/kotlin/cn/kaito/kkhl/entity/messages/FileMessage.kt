package cn.kaito.kkhl.entity.messages

import cn.kaito.kkhl.entity.objects.User

data class FileMessage(
    val type: Int,
    val code: String,
    val guild_id: String,
    val attachments: FileAttachment,
    val author: User
)

data class FileAttachment(
    val type: String,
    val name: String,
    val url: String,
    val file_type: String,
    val size: Long
)