package dev.rivu.githubrepositories.trendingprojects.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.data.injection.DataModule
import dev.rivu.githubrepositories.domain.injection.DomainModule
import dev.rivu.githubrepositories.domain.injection.FeatureScope
import dev.rivu.githubrepositories.presentation.injection.PresentationModule
import dev.rivu.githubrepositories.remote.injection.RemoteModule
import dev.rivu.githubrepositories.trendingprojects.TrendingProjectListAdapter

@Module(includes = [RemoteModule::class, DataModule::class, DomainModule::class, PresentationModule::class])
class TrendingProjectsModule {

    @Provides
    @FeatureScope
    fun provideTrendingProjectListAdapter(): TrendingProjectListAdapter {
        return TrendingProjectListAdapter()
    }
}