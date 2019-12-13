package dev.rivu.githubrepositories.domain.usecase.trendingrepositories

import dev.rivu.githubrepositories.domain.factory.TrendingProjectsFactory
import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import dev.rivu.nasaapodarchive.domain.schedulers.FakeSchedulerProvider
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SortTrendingProjectsUsecaseTest {
    private lateinit var sortTrendingProjectsUsecase: SortTrendingProjectsUsecase
    private lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setUp() {
        schedulerProvider = FakeSchedulerProvider

        sortTrendingProjectsUsecase = SortTrendingProjectsUsecase(schedulerProvider)
    }

    @Test
    fun `test sort by stars`() {
        val data = TrendingProjectsFactory.makeTrendingProjectList(10)
        val usecaseParam = SortTrendingProjectsUsecase.Params(
            data = data,
            sortBy = SortTrendingProjectsUsecase.Params.SortBy.STARS
        )

        val testObserver = sortTrendingProjectsUsecase.execute(usecaseParam).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val emittedValue = testObserver.values()[0]

        for (i in 1 until emittedValue.size) {
            assertTrue(emittedValue[i - 1].stars <= emittedValue[i].stars)
        }
    }

    @Test
    fun `test sort by name`() {
        val data = TrendingProjectsFactory.makeTrendingProjectList(10)
        val usecaseParam = SortTrendingProjectsUsecase.Params(
            data = data,
            sortBy = SortTrendingProjectsUsecase.Params.SortBy.NAME
        )

        val testObserver = sortTrendingProjectsUsecase.execute(usecaseParam).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val emittedValue = testObserver.values()[0]

        for (i in 1 until emittedValue.size) {
            assertTrue(emittedValue[i - 1].name <= emittedValue[i].name)
        }
    }
}