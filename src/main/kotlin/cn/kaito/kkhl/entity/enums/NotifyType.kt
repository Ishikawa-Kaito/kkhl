package cn.kaito.kkhl.entity.enums

import com.google.gson.*
import java.lang.reflect.Type

enum class NotifyType(val id: Int) : JsonSerializer<NotifyType>, JsonDeserializer<NotifyType> {
    All(1),
    MentionedOnly(2),
    None(3);

    companion object {
        fun get(id: Int): NotifyType {
            for (c in values()) {
                if (c.id == id) {
                    return c
                }
            }
            return All
        }
    }

    override fun serialize(src: NotifyType?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonObject().apply {
            addProperty("notify_type", src?.id)
        }
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): NotifyType {
        return get(json!!.asInt)
    }


}