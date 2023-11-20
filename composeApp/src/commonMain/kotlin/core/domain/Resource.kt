package core.domain

import io.ktor.utils.io.CancellationException

sealed interface Resource<T> {

    class Success<T>(val data: T) : Resource<T>
    class Error<T>(
        val exception: Exception,
        val message: String? = exception.message,
    ) : Resource<T>
}

suspend fun <T> tryGetData(tryGetData: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(tryGetData())
    } catch (e: CancellationException) {
        throw e
    } catch (exception: Exception) {
        Resource.Error(exception = exception)
    }
}