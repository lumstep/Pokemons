package core.configs

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

@Deprecated(message = "Don't use httpClientProvider directly, inject with koin")
internal expect fun httpClientProvider(block: HttpClientConfig<*>.() -> Unit = {}): HttpClient