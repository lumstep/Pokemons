package core.configs

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android

actual fun httpClientProvider(block: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Android){
    block()
}
