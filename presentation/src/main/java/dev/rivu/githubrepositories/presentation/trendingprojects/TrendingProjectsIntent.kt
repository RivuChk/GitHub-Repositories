package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.presentation.base.MviIntent
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation

sealed class TrendingProjectsIntent : MviIntent {
    object InitialIntent : TrendingProjectsIntent()

    data class LoadIntent(val language: String, val since: String) : TrendingProjectsIntent()

    data class RefreshIntent(val language: String, val since: String) : TrendingProjectsIntent()

    data class ClickIntent(val clickedViewPosition: Int) : TrendingProjectsIntent()

    object ClearClickIntent : TrendingProjectsIntent()

    sealed class SortIntent : TrendingProjectsIntent() {
        abstract val data: List<TrendingProjectPresentation>

        data class ByName(override val data: List<TrendingProjectPresentation>) : SortIntent()
        data class ByStars(override val data: List<TrendingProjectPresentation>) : SortIntent()
    }
}