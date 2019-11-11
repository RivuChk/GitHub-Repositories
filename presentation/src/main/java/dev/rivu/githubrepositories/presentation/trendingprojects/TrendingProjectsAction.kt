package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.presentation.base.MviAction
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation

sealed class TrendingProjectsAction : MviAction {
    data class LoadAction(val language: String, val since: String) : TrendingProjectsAction()

    data class ClickAction(val clickedViewPosition: Int) : TrendingProjectsAction()

    object ClearClickAction : TrendingProjectsAction()

    sealed class SortAction : TrendingProjectsAction() {
        abstract val data: List<TrendingProjectPresentation>

        data class ByName(override val data: List<TrendingProjectPresentation>) : SortAction()
        data class ByStars(override val data: List<TrendingProjectPresentation>) : SortAction()
    }
}