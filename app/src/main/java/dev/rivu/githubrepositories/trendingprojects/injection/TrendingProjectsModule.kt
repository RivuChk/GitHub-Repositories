package dev.rivu.githubrepositories.trendingprojects.injection

import dagger.Module
import dev.rivu.githubrepositories.data.injection.DataModule
import dev.rivu.githubrepositories.domain.injection.DomainModule
import dev.rivu.githubrepositories.presentation.injection.PresentationModule

@Module(includes = [DataModule::class, DomainModule::class, PresentationModule::class])
class TrendingProjectsModule