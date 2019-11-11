package dev.rivu.githubrepositories.presentation.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.domain.injection.FeatureScope
import dev.rivu.githubrepositories.presentation.model.mapper.PresentationToDomainMapper
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsActionProcessor
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsViewModelFactory
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @FeatureScope
    fun provideMapper(): PresentationToDomainMapper =
        PresentationToDomainMapper()

    @Provides
    @FeatureScope
    fun provideApdListViewModelFactory(
        actionProcessor: TrendingProjectsActionProcessor,
        mapper: PresentationToDomainMapper
    ): TrendingProjectsViewModelFactory =
        TrendingProjectsViewModelFactory(actionProcessor, mapper)

}