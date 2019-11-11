package dev.rivu.githubrepositories.presentation.trendingprojects

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.*
import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.usecase.trendingrepositories.TrendingProjectsUsecase
import dev.rivu.githubrepositories.presentation.factory.TrendingProjectPresentationFactory
import dev.rivu.githubrepositories.presentation.factory.TrendingProjectsFactory
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation
import dev.rivu.githubrepositories.presentation.model.mapper.PresentationToDomainMapper
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TrendingProjectsViewModelTest {
    private lateinit var actionProcessor: TrendingProjectsActionProcessor
    private lateinit var mockMapper: PresentationToDomainMapper
    private lateinit var viewModel: TrendingProjectsViewModel
    private lateinit var mockUsecase: TrendingProjectsUsecase
    private lateinit var mockStateObserver: Observer<TrendingProjectsState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mockMapper = mock()
        mockUsecase = mock()
        mockStateObserver = mock()

        actionProcessor = TrendingProjectsActionProcessor(mockUsecase)
        viewModel = TrendingProjectsViewModel(actionProcessor, mockMapper)

        viewModel.states().observeForever(mockStateObserver)
    }

    @Test
    fun `test initial intent`() {
        val domainModelList = TrendingProjectsFactory.makeTrendingProjectList(2)
        val presentationModelList = TrendingProjectPresentationFactory.makeTrendingProjectList(2)

        stubMapper(presentationModelList[0], domainModelList[0])
        stubMapper(presentationModelList[1], domainModelList[1])

        stubUsecase(Single.just(domainModelList))

        viewModel.processIntents(Observable.just(TrendingProjectsIntent.InitialIntent))

        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = true,
                    error = null
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = false,
                    error = null,
                    data = presentationModelList
                )
            )
    }

    @Test
    fun `test load intent`() {
        val domainModelList = TrendingProjectsFactory.makeTrendingProjectList(2)
        val presentationModelList = TrendingProjectPresentationFactory.makeTrendingProjectList(2)

        stubMapper(presentationModelList[0], domainModelList[0])
        stubMapper(presentationModelList[1], domainModelList[1])

        stubUsecase(Single.just(domainModelList))

        viewModel.processIntents(Observable.just(TrendingProjectsIntent.LoadIntent("", "")))

        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = true,
                    error = null
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = false,
                    error = null,
                    data = presentationModelList
                )
            )
    }

    @Test
    fun `test refresh intent`() {
        val domainModelList = TrendingProjectsFactory.makeTrendingProjectList(2)
        val presentationModelList = TrendingProjectPresentationFactory.makeTrendingProjectList(2)

        stubMapper(presentationModelList[0], domainModelList[0])
        stubMapper(presentationModelList[1], domainModelList[1])

        stubUsecase(Single.just(domainModelList))

        viewModel.processIntents(Observable.just(TrendingProjectsIntent.RefreshIntent("", "")))

        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = true,
                    error = null
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = false,
                    error = null,
                    data = presentationModelList
                )
            )
    }

    @Test
    fun `test click intent`() {
        viewModel.processIntents(Observable.just(TrendingProjectsIntent.ClickIntent(1)))

        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    clickedViewPosition = 1
                )
            )
    }

    @Test
    fun `test clear click intent`() {
        viewModel.processIntents(Observable.just(TrendingProjectsIntent.ClearClickIntent))

        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    clickedViewPosition = -1
                )
            )
    }

    @Test
    fun `test error state propagates when usecase emits error`() {
        val exception = Exception("test error")

        stubUsecase(Single.error(exception))

        viewModel.processIntents(Observable.just(TrendingProjectsIntent.LoadIntent("", "")))

        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = true,
                    error = null
                )
            )
        verify(mockStateObserver, times(1))
            .onChanged(
                TrendingProjectsState(//Loading State Update
                    isLoading = false,
                    error = exception
                )
            )
    }

    private fun stubMapper(
        presentationModel: TrendingProjectPresentation,
        domainModel: TrendingProject
    ) {
        whenever(mockMapper.mapFromDomain(domainModel))
            .thenReturn(presentationModel)
        whenever(mockMapper.mapToDomain(presentationModel))
            .thenReturn(domainModel)
    }

    private fun stubUsecase(flowable: Single<List<TrendingProject>>) {
        whenever(mockUsecase.execute(anyOrNull(), any()))
            .thenReturn(flowable)
    }
}