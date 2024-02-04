package core.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.scope.Scope

actual suspend fun Scope.provideDatabaseDriver(
    schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
): SqlDriver {
    return AndroidSqliteDriver(schema = schema.synchronous(), context = get(), name = "PokemonsDatabase.db")
}