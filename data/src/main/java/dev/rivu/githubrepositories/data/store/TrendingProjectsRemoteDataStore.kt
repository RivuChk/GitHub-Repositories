package dev.rivu.githubrepositories.data.store

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.UnsupportedOperationException

open class TrendingProjectsRemoteDataStore(val trendingProjectsRemote: TrendingProjectsRemote) :
    TrendingProjectsDataStore {
    override fun saveTrendingProjects(
        language: String,
        since: String,
        trendingProjects: List<TrendingProjectData>
    ): Completable {
        throw UnsupportedOperationException("Can't save in remote")
    }

    override fun isCached(language: String, since: String): Single<Boolean> {
        throw UnsupportedOperationException("Can't save in remote")
    }

    override fun clearTrendingProjects(): Completable {
        throw UnsupportedOperationException("Can't clear in remote data")
    }

    override fun getTrendingProjects(
        language: String,
        since: String
    ): Single<List<TrendingProjectData>> {
        return trendingProjectsRemote.getTrendingProjects(language, since)
    }

}