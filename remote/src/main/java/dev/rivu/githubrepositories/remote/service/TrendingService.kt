package dev.rivu.githubrepositories.remote.service

import dev.rivu.githubrepositories.remote.model.TrendingProjectsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingService {
    @GET("/repositories")
    fun getTrendingRepositories(@Query("language") language: String, @Query("since") since: String): Single<List<TrendingProjectsResponse>>
}