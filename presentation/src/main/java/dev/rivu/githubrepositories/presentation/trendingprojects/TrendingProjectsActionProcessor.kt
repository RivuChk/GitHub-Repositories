package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.domain.injection.FeatureScope
import dev.rivu.githubrepositories.domain.usecase.UsecaseExecutionType
import dev.rivu.githubrepositories.domain.usecase.trendingrepositories.GetTrendingProjectsUsecase
import dev.rivu.githubrepositories.domain.usecase.trendingrepositories.SortTrendingProjectsUsecase
import dev.rivu.githubrepositories.presentation.base.MviActionProcessor
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import javax.inject.Inject

@FeatureScope
open class TrendingProjectsActionProcessor @Inject constructor(
    private val getTrendingProjectsUsecase: GetTrendingProjectsUsecase,
    private val sorttrendingprojectsusecase: SortTrendingProjectsUsecase
) : MviActionProcessor<TrendingProjectsAction, TrendingProjectsResult> {
    override fun transformFromAction(): FlowableTransformer<TrendingProjectsAction, TrendingProjectsResult> {
        return FlowableTransformer { actionObservable ->
            actionObservable.publish { shared ->
                Flowable.merge(
                    shared.ofType(TrendingProjectsAction.LoadAction::class.java).compose(load()),
                    shared.ofType(TrendingProjectsAction.ClickAction::class.java).compose(click()),
                    shared.ofType(TrendingProjectsAction.ClearClickAction::class.java).compose(
                        clearClick()
                    ),
                    shared.ofType(TrendingProjectsAction.SortAction::class.java).compose(sortData())
                )
            }
        }
    }

    private fun load(): FlowableTransformer<TrendingProjectsAction.LoadAction, TrendingProjectsResult.LoadResult> {
        return FlowableTransformer { actionFlowable ->
            actionFlowable.switchMap { action ->
                getTrendingProjectsUsecase.execute(
                    GetTrendingProjectsUsecase.Params(
                        language = action.language,
                        since = action.since
                    )
                )
                    .toFlowable()
                    .map {
                        TrendingProjectsResult.LoadResult.Success(it)
                    }
                    .cast(TrendingProjectsResult.LoadResult::class.java)
                    .startWith(TrendingProjectsResult.LoadResult.InFlight)
                    .onErrorReturn {
                        TrendingProjectsResult.LoadResult.Failure(it)
                    }

            }
        }
    }

    private fun click(): FlowableTransformer<TrendingProjectsAction.ClickAction, TrendingProjectsResult.ClickResult> {
        return FlowableTransformer { actionFlowable ->
            actionFlowable.switchMap { action ->
                Flowable.just(TrendingProjectsResult.ClickResult(action.clickedViewPosition))
            }
        }
    }

    private fun clearClick(): FlowableTransformer<TrendingProjectsAction.ClearClickAction, TrendingProjectsResult.ClearClickResult> {
        return FlowableTransformer { actionFlowable ->
            actionFlowable.switchMap {
                Flowable.just(TrendingProjectsResult.ClearClickResult)
            }
        }
    }

    private fun sortData(): FlowableTransformer<TrendingProjectsAction.SortAction, TrendingProjectsResult.SortResult> {
        return FlowableTransformer { actionFlowable ->
            actionFlowable.switchMap { action ->
                sorttrendingprojectsusecase
                    .execute(
                        SortTrendingProjectsUsecase.Params(
                            data = action.data,
                            sortBy = when (action) {
                                is TrendingProjectsAction.SortAction.ByName -> SortTrendingProjectsUsecase.Params.SortBy.NAME
                                is TrendingProjectsAction.SortAction.ByStars -> SortTrendingProjectsUsecase.Params.SortBy.STARS
                            }
                        ),
                        UsecaseExecutionType.Computation
                    )
                    .toFlowable()
                    .map {
                        TrendingProjectsResult.SortResult(it)
                    }
            }

        }
    }

}