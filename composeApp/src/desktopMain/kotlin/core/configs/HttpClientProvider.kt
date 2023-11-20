package core.configs

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache5.Apache5

actual fun httpClientProvider(): HttpClient = HttpClient(Apache5)
