package dev.rivu.githubrepositories.cache.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.cache.TrendingProjectsCacheImpl
import dev.rivu.githubrepositories.data.source.TrendingProjectsCache
import javax.inject.Singleton

@Module
class CacheModule {
    @Provides
    @Singleton
    fun provideTrendingProjectsCache(): TrendingProjectsCache {
        return TrendingProjectsCacheImpl()
    }
}