package cn.kaito.kkhl.network

import kotlinx.serialization.Serializable


data class HttpResponse(
    val code: Int,
    val message: String,
    val data: Map<String, String>
)


data class WSFrame(
    val s: Int,
    val d: Map<String,Any>,
    val sn: Int?
)

data class WSData(
    val code: Int,
    val sn: Int,
    val d: String
)
