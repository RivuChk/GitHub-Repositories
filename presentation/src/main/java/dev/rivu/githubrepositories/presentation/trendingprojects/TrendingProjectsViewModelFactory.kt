package dev.rivu.githubrepositories.presentation.trendingprojects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rivu.githubrepositories.presentation.model.mapper.PresentationToDomainMapper
import javax.inject.Inject

class TrendingProjectsViewModelFactory @Inject constructor(
    private val actionProcessor: TrendingProjectsActionProcessor,
    private val apodViewMapper: PresentationToDomainMapper
) : ViewModelProvider.Factory {
    private lateinit var apodListViewModel: TrendingProjectsViewModel
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == TrendingProjectsViewModel::class.java) {
            if (!::apodListViewModel.isInitialized) {
                apodListViewModel = TrendingProjectsViewModel(actionProcessor, apodViewMapper)
            }
            return apodListViewModel as T
        } else {
            throw UnsupportedOperationException("TrendingProjectsViewModelFactory can only produce TrendingProjectsViewModel")
        }
    }
}