package dev.rivu.githubrepositories.data.store

import com.nhaarman.mockito_kotlin.*
import dev.rivu.githubrepositories.data.factory.TrendingProjectsDataFactory
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TrendingProjectsRemoteDataStoreTest {
    lateinit var trendingProjectsRemote: TrendingProjectsRemote
    lateinit var trendingProjectsRemoteDataStore: TrendingProjectsRemoteDataStore

    @Before
    fun setup() {
        trendingProjectsRemote = mock()
        trendingProjectsRemoteDataStore = TrendingProjectsRemoteDataStore(trendingProjectsRemote)
    }

    @Test
    fun `getTrendingProjects should emit, same data as remote`() {
        val dummyData = TrendingProjectsDataFactory.makeTrendingProjectList()
        whenever(trendingProjectsRemote.getTrendingProjects(anyString(), anyString()))
            .thenReturn(Single.just(dummyData))

        val testObserver = trendingProjectsRemoteDataStore.getTrendingProjects("", "").test()
        verify(trendingProjectsRemote, times(1)).getTrendingProjects("", "")
        verifyNoMoreInteractions(trendingProjectsRemote)

        testObserver.assertValue(dummyData)
    }

}