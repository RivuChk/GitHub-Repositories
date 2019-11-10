package dev.rivu.githubrepositories.data

import com.nhaarman.mockito_kotlin.*
import dev.rivu.githubrepositories.data.TrendingProjectsRepositoryImpl
import dev.rivu.githubrepositories.data.factory.TrendingProjectsDataFactory
import dev.rivu.githubrepositories.data.factory.TrendingProjectsFactory
import dev.rivu.githubrepositories.data.model.mapper.DataToDomainMapper
import dev.rivu.githubrepositories.data.store.TrendingProjectsDataStore
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TrendingProjectsRepositoryImplTest {
    lateinit var trendingProjectsRepositoryImpl: TrendingProjectsRepositoryImpl
    lateinit var cacheDataStore: TrendingProjectsDataStore
    lateinit var remoteDataStore: TrendingProjectsDataStore

    lateinit var mapper: DataToDomainMapper

    @Before
    fun setup() {
        mapper = mock()
        cacheDataStore = mock()
        remoteDataStore = mock()

        trendingProjectsRepositoryImpl =
            TrendingProjectsRepositoryImpl(cacheDataStore, remoteDataStore, mapper)
    }

    @Test
    fun `getTrendingProjects should call remote`() {
        val dummyTrendingProjects = TrendingProjectsDataFactory.makeTrendingProjectList()
        whenever(remoteDataStore.getTrendingProjects(anyString(), anyString()))
            .thenReturn(Single.just(dummyTrendingProjects))

        whenever(mapper.mapToDomain(any()))
            .thenReturn(TrendingProjectsFactory.makeTrendingProject())

        val testObserver = trendingProjectsRepositoryImpl.getTrendingProjects("", "").test()
        verify(remoteDataStore, times(1)).getTrendingProjects("", "")
        testObserver.assertComplete()
    }

    @Test
    fun `getTrendingProjects should emit error, emitted by remote`() {
        val exception = Exception("Test Error")
        whenever(remoteDataStore.getTrendingProjects(anyString(), anyString()))
            .thenReturn(Single.error(exception))

        val testObserver = trendingProjectsRepositoryImpl.getTrendingProjects("", "").test()
        verify(remoteDataStore, times(1)).getTrendingProjects("", "")
        testObserver.assertError(exception)
    }
}