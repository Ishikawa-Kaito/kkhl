package cn.kaito.kkhl.entity.messages

import cn.kaito.kkhl.entity.objects.User

data class VideoMessage(
    val type: Int,
    val code: String,
    val guild_id: String,
    val attachments: VideoAttachment,
    val author: User
)

data class VideoAttachment(
    val type: String,
    val name: String,
    val url: String,
    val file_type: String,
    val size: Long,
    val duration: Double,
    val width: Int,
    val height: Int
)