package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.presentation.base.MviAction

sealed class TrendingProjectsAction : MviAction {
    data class LoadAction(val language: String, val since: String) : TrendingProjectsAction()

    data class ClickAction(val clickedViewPosition: Int) : TrendingProjectsAction()

    object ClearClickAction : TrendingProjectsAction()
}