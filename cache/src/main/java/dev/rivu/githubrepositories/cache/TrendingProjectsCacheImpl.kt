package dev.rivu.githubrepositories.cache

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.data.source.TrendingProjectsCache
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

//NOTE: Cache isn't yet implemented

class TrendingProjectsCacheImpl : TrendingProjectsCache {
    override fun getTrendingProjects(
        language: String,
        since: String
    ): Maybe<List<TrendingProjectData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTrendingProjects(
        language: String,
        since: String,
        trendingProjects: List<TrendingProjectData>
    ): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(language: String, since: String): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearTrendingProjects(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}