package dev.rivu.githubrepositories.remote

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import dev.rivu.githubrepositories.remote.model.mapper.ResponseToDataMapper
import dev.rivu.githubrepositories.remote.service.TrendingService
import io.reactivex.Single

class TrendingProjectsRemoteImpl(
    private val trendingService: TrendingService,
    private val mapper: ResponseToDataMapper
) : TrendingProjectsRemote {

    override fun getTrendingProjects(
        language: String,
        since: String
    ): Single<List<TrendingProjectData>> {
        return trendingService.getTrendingRepositories(language, since)
            .map { projectsList ->
                projectsList.map {
                    mapper.mapToDomain(it)
                }
            }
    }

}