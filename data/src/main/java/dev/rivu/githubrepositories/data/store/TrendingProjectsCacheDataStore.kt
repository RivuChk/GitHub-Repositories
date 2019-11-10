package dev.rivu.githubrepositories.data.store

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.data.source.TrendingProjectsCache
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.UnsupportedOperationException

open class TrendingProjectsCacheDataStore(val trendingProjectsCache: TrendingProjectsCache) :
    TrendingProjectsDataStore {
    override fun saveTrendingProjects(
        language: String,
        since: String,
        trendingProjects: List<TrendingProjectData>
    ): Completable {
        return trendingProjectsCache.saveTrendingProjects(language, since, trendingProjects)
    }

    override fun isCached(language: String, since: String): Single<Boolean> {
        return trendingProjectsCache.isCached(language, since)
    }

    override fun clearTrendingProjects(): Completable {
        return trendingProjectsCache.clearTrendingProjects()
    }

    override fun getTrendingProjects(
        language: String,
        since: String
    ): Single<List<TrendingProjectData>> {
        return trendingProjectsCache.getTrendingProjects(language, since)
            .toSingle()
    }

}