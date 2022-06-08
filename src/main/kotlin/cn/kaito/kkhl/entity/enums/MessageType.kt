package cn.kaito.kkhl.entity.enums

import com.google.gson.*
import java.lang.reflect.Type

enum class MessageType(val id: Int) : JsonSerializer<MessageType>, JsonDeserializer<MessageType> {
    Text(1),
    Pic(2),
    Video(3),
    File(4),
    Audio(8),
    KMarkdown(9),
    Card(10),
    System(255);

    companion object {
        fun get(id: Int): MessageType {
            for (c in values()) {
                if (c.id == id) {
                    return c
                }
            }
            return Text
        }
    }

    override fun serialize(src: MessageType?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonObject().apply {
            addProperty("type", src?.id)
        }
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MessageType {
        return get(json!!.asInt)
    }


}