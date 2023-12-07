package core.di

import org.lumstep.PokemonsDatabase
import core.configs.httpClientProvider
import core.database.provideDatabaseDriver
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.lumstep.PokemonsQueries

val htpClientModule = module {
    single {
        @Suppress("DEPRECATION")
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

val databaseModule = module {
    single {
        runBlocking {
            provideDatabaseDriver(PokemonsDatabase.Schema)
        }
    }

    single { PokemonsQueries(get()) }
}