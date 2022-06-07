package cn.kaito.kkhl.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*

class RequestHandler(private val token: String) {
    private val baseUrl = "https://www.kaiheila.cn/api/v3"

    private val httpClient = HttpClient {
        install(Logging)
        install(ContentNegotiation) {
            gson()
        }
    }


    private suspend fun http(url: String) = httpClient.get(url) {
        header(HttpHeaders.Authorization, "Bot $token")
    }.body<HttpResponse>()


}