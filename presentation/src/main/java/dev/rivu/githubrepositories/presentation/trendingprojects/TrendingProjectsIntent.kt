package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.presentation.base.MviIntent

sealed class TrendingProjectsIntent : MviIntent {
    object InitialIntent : TrendingProjectsIntent()

    data class LoadIntent(val language: String, val since: String) : TrendingProjectsIntent()

    data class RefreshIntent(val language: String, val since: String) : TrendingProjectsIntent()

    data class ClickIntent(val clickedViewPosition: Int) : TrendingProjectsIntent()

    object ClearClickIntent : TrendingProjectsIntent()
}