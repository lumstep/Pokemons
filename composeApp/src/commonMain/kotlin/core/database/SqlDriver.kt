package core.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import org.koin.core.scope.Scope

expect suspend fun Scope.provideDatabaseDriver(
    schema: SqlSchema<QueryResult.AsyncValue<Unit>>
): SqlDriver