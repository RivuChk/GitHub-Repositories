package dev.rivu.githubrepositories.data.store

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import io.reactivex.Completable
import io.reactivex.Single

interface TrendingProjectsDataStore {
    fun getTrendingProjects(language: String, since: String): Single<List<TrendingProjectData>>

    fun saveTrendingProjects(
        language: String,
        since: String,
        trendingProjects: List<TrendingProjectData>
    ): Completable

    fun isCached(language: String, since: String): Single<Boolean>

    fun clearTrendingProjects(): Completable
}