package dev.rivu.githubrepositories.domain.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import dev.rivu.githubrepositories.domain.schedulers.SchedulerProviderImpl
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImpl

}