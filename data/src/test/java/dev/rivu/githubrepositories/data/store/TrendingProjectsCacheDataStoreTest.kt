package dev.rivu.githubrepositories.data.store

import com.nhaarman.mockito_kotlin.*
import dev.rivu.githubrepositories.data.factory.TrendingProjectsDataFactory
import dev.rivu.githubrepositories.data.source.TrendingProjectsCache
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.NoSuchElementException

class TrendingProjectsCacheDataStoreTest {
    lateinit var trendingProjectsCache: TrendingProjectsCache
    lateinit var trendingProjectsCacheDataStore: TrendingProjectsCacheDataStore

    @Before
    fun setup() {
        trendingProjectsCache = mock()
        trendingProjectsCacheDataStore = TrendingProjectsCacheDataStore(trendingProjectsCache)
    }

    @Test
    fun `saveTrendingProjects should call cache`() {
        val dummyData = TrendingProjectsDataFactory.makeTrendingProjectList()
        trendingProjectsCacheDataStore.saveTrendingProjects("", "", dummyData)
        verify(trendingProjectsCache, times(1)).saveTrendingProjects("", "", dummyData)
        verifyNoMoreInteractions(trendingProjectsCache)
    }

    @Test
    fun `isCached should emit what cache emits`() {
        whenever(trendingProjectsCache.isCached(anyString(), anyString()))
            .thenReturn(Single.just(true))

        val testObserver = trendingProjectsCacheDataStore.isCached("", "").test()
        verify(trendingProjectsCache, times(1)).isCached("", "")
        verifyNoMoreInteractions(trendingProjectsCache)

        testObserver.assertValue(true)
    }

    @Test
    fun `getTrendingProjects should emit, if cache isn't empty`() {
        val dummyData = TrendingProjectsDataFactory.makeTrendingProjectList()
        whenever(trendingProjectsCache.getTrendingProjects(anyString(), anyString()))
            .thenReturn(Maybe.just(dummyData))

        val testObserver = trendingProjectsCacheDataStore.getTrendingProjects("", "").test()
        verify(trendingProjectsCache, times(1)).getTrendingProjects("", "")
        verifyNoMoreInteractions(trendingProjectsCache)

        testObserver.assertValue(dummyData)
    }

    @Test
    fun `getTrendingProjects should emit error, if cache is empty`() {
        whenever(trendingProjectsCache.getTrendingProjects(anyString(), anyString()))
            .thenReturn(Maybe.empty())

        val testObserver = trendingProjectsCacheDataStore.getTrendingProjects("", "").test()
        verify(trendingProjectsCache, times(1)).getTrendingProjects("", "")
        verifyNoMoreInteractions(trendingProjectsCache)

        testObserver.assertError {
            it is NoSuchElementException
        }
    }
}