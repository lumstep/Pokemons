package core.paging

import app.cash.paging.ExperimentalPagingApi
import app.cash.paging.PagingSource

/***
 * Class copied from :
 * https://android.googlesource.com/platform/frameworks/support/+/8f765461657ca2ef3a4dc789dd4803bedeafc925/paging/common/src/main/kotlin/androidx/paging/InvalidatingPagingSourceFactory.kt
 */
@ExperimentalPagingApi
class InvalidatingPagingSourceFactory<Key : Any, Value : Any>(
    private val pagingSourceFactory: () -> PagingSource<Key, Value>
) {
    private val pagingSources = mutableListOf<PagingSource<Key, Value>>()

    operator fun invoke() = pagingSourceFactory().also { pagingSources.add(it) }

    fun invalidate() {
        while (pagingSources.isNotEmpty()) {
            pagingSources.removeFirst().also {
                if (!it.invalid) {
                    it.invalidate()
                }
            }
        }
    }
}