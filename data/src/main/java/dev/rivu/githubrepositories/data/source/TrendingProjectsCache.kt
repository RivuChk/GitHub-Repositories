package dev.rivu.githubrepositories.data.source

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface TrendingProjectsCache {
    fun getTrendingProjects(language: String, since: String): Maybe<List<TrendingProjectData>>

    fun saveTrendingProjects(
        language: String,
        since: String,
        trendingProjects: List<TrendingProjectData>
    ): Completable

    fun isCached(language: String, since: String): Single<Boolean>

    fun clearTrendingProjects(): Completable
}