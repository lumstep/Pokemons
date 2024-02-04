package core.configs

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

actual fun httpClientProvider(block: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(block)
