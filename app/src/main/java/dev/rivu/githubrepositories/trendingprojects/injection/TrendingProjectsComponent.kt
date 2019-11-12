package dev.rivu.githubrepositories.trendingprojects.injection

import dagger.BindsInstance
import dagger.Component
import dev.rivu.githubrepositories.data.injection.DataModule
import dev.rivu.githubrepositories.domain.injection.DomainModule
import dev.rivu.githubrepositories.domain.injection.FeatureScope
import dev.rivu.githubrepositories.injection.CoreComponent
import dev.rivu.githubrepositories.presentation.injection.PresentationModule
import dev.rivu.githubrepositories.remote.injection.RemoteModule
import dev.rivu.githubrepositories.trendingprojects.TrendingProjectsActivity

@FeatureScope
@Component(modules = [TrendingProjectsModule::class], dependencies = [CoreComponent::class])
interface TrendingProjectsComponent {

    fun inject(activity: TrendingProjectsActivity)

    @Component.Builder
    interface Builder {
        fun build(): TrendingProjectsComponent
        @BindsInstance
        fun trendingProjectsActivity(activity: TrendingProjectsActivity): Builder

        fun coreComponent(module: CoreComponent): Builder
        fun trendingProjectsModule(module: TrendingProjectsModule): Builder
        fun dataModule(module: DataModule): Builder
        fun domainModule(module: DomainModule): Builder
        fun presentationModule(module: PresentationModule): Builder
        fun remoteModule(remoteModule: RemoteModule): Builder
    }
}