package dev.rivu.githubrepositories.remote.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import dev.rivu.githubrepositories.remote.TrendingProjectsRemoteImpl
import dev.rivu.githubrepositories.remote.model.mapper.ResponseToDataMapper
import dev.rivu.githubrepositories.remote.service.TrendingService
import dev.rivu.githubrepositories.remote.service.TrendingServiceFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideTrendingService(@Named("baseUrl") baseUrl: String, cacheDir: File): TrendingService =
        TrendingServiceFactory(baseUrl, cacheDir).makeTrendingService()

    @Provides
    @Singleton
    fun provideMapper(): ResponseToDataMapper = ResponseToDataMapper()

    @Provides
    @Singleton
    fun providesRemote(
        service: TrendingService,
        mapper: ResponseToDataMapper
    ): TrendingProjectsRemote =
        TrendingProjectsRemoteImpl(service, mapper)
}