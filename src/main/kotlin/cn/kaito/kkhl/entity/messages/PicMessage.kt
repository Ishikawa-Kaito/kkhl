package cn.kaito.kkhl.entity.messages

data class PicMessage(
    val type: Int,
    val code: String,
    val guild_id: String,
    val attachments: PicAttachment
)

data class PicAttachment(
    val type: String,
    val name: String,
    val url: String
)