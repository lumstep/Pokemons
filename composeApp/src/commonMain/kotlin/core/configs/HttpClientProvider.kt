package core.configs

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Deprecated(
    replaceWith = ReplaceWith("HttpClientKeeper.httpClient"),
    message = "Don't use httpClientProvider directly, use HttpClientKeeper instead"
)
internal expect fun httpClientProvider(block: HttpClientConfig<*>.() -> Unit = {}): HttpClient

object HttpClientKeeper {

    @Suppress("DEPRECATION")
    val httpClient by lazy {
        httpClientProvider {
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }
}