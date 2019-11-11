package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.presentation.base.MviResult
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation

sealed class TrendingProjectsResult : MviResult {
    sealed class LoadResult : TrendingProjectsResult() {
        data class Success(
            val trendingProjects: List<TrendingProject>
        ) : LoadResult()

        data class Failure(
            val error: Throwable
        ) : LoadResult()

        object InFlight : LoadResult()
    }

    class ClickResult(val clickedViewPosition: Int) : TrendingProjectsResult()

    object ClearClickResult : TrendingProjectsResult()
}