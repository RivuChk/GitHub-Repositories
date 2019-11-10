package dev.rivu.githubrepositories.data.source

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface TrendingProjectsRemote {
    fun getTrendingProjects(language: String, since: String): Single<List<TrendingProjectData>>
}