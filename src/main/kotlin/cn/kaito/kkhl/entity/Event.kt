package cn.kaito.kkhl.entity

data class Event(
    val channel_type: ChannelType,
    val type: MessageType,
    val target_id: String,
    val author_id: String,
    val content: String,
    val msg_id: String,
    val msg_timestamp: Int,
    val nonce: String,
    val extra: Extra
)

data class Extra(
    val type: MessageType,
    val guild_id: String,
    val channel_name: String,
    val mention: List<String>,
    val mention_all: Boolean,
    val mention_roles: List<String>,
    val mention_here: Boolean,
    val author: User
)

enum class ChannelType {
    GROUP,
    PERSON,
    BROADCAST
}

enum class MessageType(val id: Int) {
    Text(1),
    Pic(2),
    Video(3),
    File(4),
    Audio(8),
    KMarkdown(9),
    Card(10),
    System(255)
}