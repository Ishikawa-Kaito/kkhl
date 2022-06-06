package cn.kaito.kkhl.network

import kotlinx.serialization.Serializable


@Serializable
data class HttpResponse(
    val code: Int,
    val message: String,
    val data: Map<String, String>
)


@Serializable
data class WSFrame(
    val s: Int
)

@Serializable
data class WSData(
    val code: Int,
    val sn: Int,
    val d: String
)
