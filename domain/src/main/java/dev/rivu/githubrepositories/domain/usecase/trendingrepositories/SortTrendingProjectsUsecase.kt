package dev.rivu.githubrepositories.domain.usecase.trendingrepositories

import dev.rivu.githubrepositories.domain.injection.FeatureScope
import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import dev.rivu.githubrepositories.domain.usecase.BaseSingleUsecase
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
open class SortTrendingProjectsUsecase @Inject constructor(
    schedulerProvider: SchedulerProvider
) : BaseSingleUsecase<List<TrendingProject>, SortTrendingProjectsUsecase.Params>(schedulerProvider) {

    override fun buildUseCase(param: Params): Single<List<TrendingProject>> {
        return Single.defer {
            val updatedData = if (param.sortBy == Params.SortBy.NAME) {
                param.data.sortedBy {
                    it.name
                }
            } else {
                param.data.sortedBy {
                    it.stars
                }
            }
            Single.just(updatedData)
        }
    }

    data class Params(
        val data: List<TrendingProject>,
        val sortBy: SortBy
    ) {
        enum class SortBy {
            NAME,
            STARS
        }
    }
}