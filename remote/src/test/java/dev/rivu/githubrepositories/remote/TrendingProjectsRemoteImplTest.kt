package dev.rivu.githubrepositories.remote

import com.nhaarman.mockito_kotlin.*
import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.remote.factory.TrendingProjectsDataFactory
import dev.rivu.githubrepositories.remote.factory.TrendingProjectsResponseFactory
import dev.rivu.githubrepositories.remote.model.TrendingProjectsResponse
import dev.rivu.githubrepositories.remote.model.mapper.ResponseToDataMapper
import dev.rivu.githubrepositories.remote.service.TrendingService
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TrendingProjectsRemoteImplTest {
    lateinit var trendingProjectsRemoteImpl: TrendingProjectsRemoteImpl
    lateinit var trendingService: TrendingService
    lateinit var mapper: ResponseToDataMapper

    @Before
    fun setup() {
        trendingService = mock()
        mapper = mock()

        trendingProjectsRemoteImpl = TrendingProjectsRemoteImpl(trendingService, mapper)
    }

    @Test
    fun `getTrendingProjects should emit data emitted by service`() {
        val dummyResponse = TrendingProjectsResponseFactory.makeTrendingProjectList()
        val dummyData = TrendingProjectsDataFactory.makeTrendingProjectList()

        stubResponse(dummyResponse, dummyData)

        val testObserver = trendingProjectsRemoteImpl.getTrendingProjects("", "").test()
        verify(trendingService, times(1)).getTrendingRepositories(anyString(), anyString())
        verifyNoMoreInteractions(trendingService)
        verify(mapper, times(dummyData.size)).mapToDomain(any())
        verifyNoMoreInteractions(mapper)

        testObserver.assertValue(dummyData)
    }

    @Test
    fun `getTrendingProjects should emit error emitted by service`() {
        val exception = Exception("test error")

        whenever(trendingService.getTrendingRepositories(anyString(), anyString()))
            .thenReturn(Single.error(exception))

        val testObserver = trendingProjectsRemoteImpl.getTrendingProjects("", "").test()
        verify(trendingService, times(1)).getTrendingRepositories(anyString(), anyString())
        verifyNoMoreInteractions(trendingService)
        verify(mapper, never()).mapToDomain(any())

        testObserver.assertError(exception)
    }

    private fun stubResponse(
        dummyResponse: List<TrendingProjectsResponse>,
        dummyData: List<TrendingProjectData>
    ) {
        assertEquals(dummyData.size, dummyResponse.size)
        whenever(trendingService.getTrendingRepositories(anyString(), anyString()))
            .thenReturn(Single.just(dummyResponse))

        for (i in dummyData.indices) {
            whenever(mapper.mapToDomain(dummyResponse[i]))
                .thenReturn(dummyData[i])
        }
    }
}