package dev.rivu.githubrepositories.presentation.trendingprojects

import dev.rivu.githubrepositories.presentation.base.MviState
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation

data class TrendingProjectsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val clickedViewPosition: Int = -1,
    val data: List<TrendingProjectPresentation> = emptyList(),
    val resetScrollState: Boolean = false
) : MviState {
    companion object {
        @JvmStatic
        fun idle() = TrendingProjectsState()
    }
}