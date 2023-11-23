package core.configs

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.apache5.Apache5

actual fun httpClientProvider(block: HttpClientConfig<*>.() -> Unit): HttpClient =
    HttpClient(
        engineFactory = Apache5,
        block = block,
    )
