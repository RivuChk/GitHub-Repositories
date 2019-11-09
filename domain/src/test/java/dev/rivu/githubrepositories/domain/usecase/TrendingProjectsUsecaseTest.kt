package dev.rivu.githubrepositories.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import dev.rivu.githubrepositories.domain.factory.TrendingProjectsFactory
import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.repository.TrendingProjectRepository
import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import dev.rivu.githubrepositories.domain.usecase.trendingrepositories.TrendingProjectsUsecase
import dev.rivu.nasaapodarchive.domain.schedulers.FakeSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TrendingProjectsUsecaseTest {
    private lateinit var trendingProjectsUsecase: TrendingProjectsUsecase
    private lateinit var schedulerProvider: SchedulerProvider
    private lateinit var mockTrendingProjectRepository: TrendingProjectRepository

    private lateinit var usecaseParam: TrendingProjectsUsecase.Params

    @Before
    fun setup() {
        usecaseParam = TrendingProjectsUsecase.Params()
        schedulerProvider = FakeSchedulerProvider
        mockTrendingProjectRepository = mock()

        trendingProjectsUsecase = TrendingProjectsUsecase(
            mockTrendingProjectRepository,
            schedulerProvider
        )
    }

    @Test
    fun `execute should call repository`() {
        stubRepositoryData()
        trendingProjectsUsecase.execute(usecaseParam)
        verify(mockTrendingProjectRepository, times(1)).getTrendingProjects("", "")
    }

    @Test
    fun `execute should emit same data as repository`() {
        val stubData = TrendingProjectsFactory.makeTrendingProjectList(10)
        stubRepositoryData(stubData)
        val testObserver = trendingProjectsUsecase.execute(usecaseParam).test()
        verify(mockTrendingProjectRepository, times(1)).getTrendingProjects("", "")
        testObserver.assertValue(stubData)
    }

    private fun stubRepositoryData(
        stubData: List<TrendingProject> = TrendingProjectsFactory.makeTrendingProjectList(
            10
        )
    ) {
        whenever(mockTrendingProjectRepository.getTrendingProjects(anyString(), anyString()))
            .thenReturn(Single.just(stubData))
    }
}