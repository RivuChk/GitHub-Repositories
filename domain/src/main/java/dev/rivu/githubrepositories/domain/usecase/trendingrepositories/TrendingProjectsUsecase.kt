package dev.rivu.githubrepositories.domain.usecase.trendingrepositories

import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.repository.TrendingProjectRepository
import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import dev.rivu.githubrepositories.domain.usecase.BaseSingleUsecase
import io.reactivex.Single
import javax.inject.Inject

class TrendingProjectsUsecase @Inject constructor(
    private val trendingProjectRepository: TrendingProjectRepository,
    schedulerProvider: SchedulerProvider
) : BaseSingleUsecase<List<TrendingProject>, TrendingProjectsUsecase.Params>(schedulerProvider) {

    override fun buildUseCase(param: Params): Single<List<TrendingProject>> {
        return trendingProjectRepository
            .getTrendingProjects(param.language, param.since)
    }

    data class Params(
        val language: String = "",
        val since: String = ""
    )
}