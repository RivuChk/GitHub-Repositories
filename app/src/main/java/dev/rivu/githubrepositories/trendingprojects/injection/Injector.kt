@file:JvmName("Injector")

package dev.rivu.githubrepositories.trendingprojects.injection

import dev.rivu.githubrepositories.coreComponent
import dev.rivu.githubrepositories.data.injection.DataModule
import dev.rivu.githubrepositories.domain.injection.DomainModule
import dev.rivu.githubrepositories.presentation.injection.PresentationModule
import dev.rivu.githubrepositories.trendingprojects.TrendingProjectsActivity

fun TrendingProjectsActivity.inject() {
    DaggerTrendingProjectsComponent.builder()
        .coreComponent(coreComponent())
        .trendingProjectsModule(TrendingProjectsModule())
        .dataModule(DataModule())
        .domainModule(DomainModule())
        .presentationModule(PresentationModule())
        .trendingProjectsActivity(this)
        .build()
        .inject(this)
}