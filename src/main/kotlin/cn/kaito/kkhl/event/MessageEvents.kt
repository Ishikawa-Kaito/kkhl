package cn.kaito.kkhl.event

import cn.kaito.kkhl.entity.messages.*
import cn.kaito.kkhl.event.base.EventExtraBase

class TextMessageEvent : EventExtraBase {
    override val type: Any
        get() = 1

    override lateinit var body: TextMessage
}

class FileMessageEvent : EventExtraBase {
    override val type: Any
        get() = 4

    override lateinit var body: FileMessage
}

class PicMessageEvent : EventExtraBase {
    override val type: Any
        get() = 2

    override lateinit var body: PicMessage
}

class VideoMessageEvent : EventExtraBase {
    override val type: Any
        get() = 3

    override lateinit var body: VideoMessage
}

class KMarkdownMessageEvent : EventExtraBase {
    override val type: Any
        get() = 9

    override lateinit var body: KMarkdownMessage
}

