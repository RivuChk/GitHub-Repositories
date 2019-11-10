package dev.rivu.githubrepositories.data.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.data.TrendingProjectsRepositoryImpl
import dev.rivu.githubrepositories.data.model.mapper.DataToDomainMapper
import dev.rivu.githubrepositories.data.source.TrendingProjectsCache
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import dev.rivu.githubrepositories.data.store.TrendingProjectsCacheDataStore
import dev.rivu.githubrepositories.data.store.TrendingProjectsRemoteDataStore
import dev.rivu.githubrepositories.domain.repository.TrendingProjectRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    @Named("cache")
    fun provideTrendingProjectsCacheDataStore(trendingProjectsCache: TrendingProjectsCache): TrendingProjectsCacheDataStore {
        return TrendingProjectsCacheDataStore(trendingProjectsCache)
    }

    @Provides
    @Singleton
    @Named("remote")
    fun provideTrendingProjectsRemoteDataStore(trendingProjectsRemote: TrendingProjectsRemote): TrendingProjectsRemoteDataStore {
        return TrendingProjectsRemoteDataStore(trendingProjectsRemote)
    }

    @Provides
    @Singleton
    fun providesTrendingProjectsMapper(
    ): DataToDomainMapper {
        return DataToDomainMapper()
    }

    @Provides
    @Singleton
    fun providesTrendingProjectsRepository(
        @Named("cache") cacheDataStore: TrendingProjectsCacheDataStore,
        @Named("remote") remoteDataStore: TrendingProjectsRemoteDataStore,
        mapper: DataToDomainMapper
    ): TrendingProjectRepository {
        return TrendingProjectsRepositoryImpl(cacheDataStore, remoteDataStore, mapper)
    }
}