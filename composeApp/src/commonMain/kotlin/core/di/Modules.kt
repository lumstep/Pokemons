package core.di

import app.cash.sqldelight.db.SqlDriver
import com.javiersc.kotlinx.coroutines.run.blocking.runBlocking
import core.configs.httpClientProvider
import core.database.provideDatabaseDriver
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.lumstep.PokemonsDatabase
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
    single<SqlDriver> {
        runBlocking<SqlDriver> {
            provideDatabaseDriver(PokemonsDatabase.Schema)
        }
    }
    single { PokemonsQueries(get()) }
}