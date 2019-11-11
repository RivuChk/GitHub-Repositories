package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.presentation.base.BaseViewModel
import dev.rivu.githubrepositories.presentation.model.mapper.PresentationToDomainMapper
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class TrendingProjectsViewModel @Inject constructor(
    override val actionProcessor: TrendingProjectsActionProcessor,
    private val mapper: PresentationToDomainMapper
) : BaseViewModel<TrendingProjectsIntent, TrendingProjectsState, TrendingProjectsAction, TrendingProjectsResult>() {

    override fun intentFilter(): FlowableTransformer<TrendingProjectsIntent, TrendingProjectsIntent> {
        return FlowableTransformer { intents ->
            intents.publish { shared ->
                Flowable.merge<TrendingProjectsIntent>(
                    shared.ofType(TrendingProjectsIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is TrendingProjectsIntent.InitialIntent }
                )
            }
        }
    }

    override fun actionFromIntent(intent: TrendingProjectsIntent): TrendingProjectsAction {
        return when(intent) {
            is TrendingProjectsIntent.InitialIntent -> TrendingProjectsAction.LoadAction("", "")
            is TrendingProjectsIntent.LoadIntent -> TrendingProjectsAction.LoadAction(intent.language, intent.since)
            is TrendingProjectsIntent.RefreshIntent -> TrendingProjectsAction.LoadAction(intent.language, intent.since)
            is TrendingProjectsIntent.ClickIntent -> TrendingProjectsAction.ClickAction(intent.clickedViewPosition)
            is TrendingProjectsIntent.ClearClickIntent -> TrendingProjectsAction.ClearClickAction
            is TrendingProjectsIntent.SortIntent.ByName -> TrendingProjectsAction.SortAction.ByName(intent.data)
            is TrendingProjectsIntent.SortIntent.ByStars -> TrendingProjectsAction.SortAction.ByStars(intent.data)
        }
    }

    override fun reducer(): BiFunction<TrendingProjectsState, TrendingProjectsResult, TrendingProjectsState> {
        return BiFunction { previousState, result ->
            when (result) {
                is TrendingProjectsResult.LoadResult.Success ->
                    previousState.copy(
                        isLoading = false,
                        error = null,
                        data = result.trendingProjects.map(mapper::mapFromDomain)
                    )
                is TrendingProjectsResult.LoadResult.InFlight ->
                    previousState.copy(
                        isLoading = true,
                        error = null,
                        resetScrollState = false
                    )
                is TrendingProjectsResult.LoadResult.Failure ->
                    previousState.copy(
                        isLoading = false,
                        error = result.error
                    )
                is TrendingProjectsResult.ClickResult ->
                    previousState.copy(
                        clickedViewPosition = result.clickedViewPosition,
                        resetScrollState = false
                    )
                is TrendingProjectsResult.ClearClickResult ->
                    previousState.copy(
                        clickedViewPosition = -1
                    )
                is TrendingProjectsResult.SortResult ->
                    previousState.copy(
                        isLoading = false,
                        data = result.trendingProjects,
                        resetScrollState = true
                    )
            }
        }
    }

    override fun initialState(): TrendingProjectsState = TrendingProjectsState.idle()
}