package dev.rivu.githubrepositories.domain.repository

import dev.rivu.githubrepositories.domain.model.TrendingProject
import io.reactivex.Single

interface TrendingProjectRepository {
    fun getTrendingProjects(language: String, since: String): Single<List<TrendingProject>>
}