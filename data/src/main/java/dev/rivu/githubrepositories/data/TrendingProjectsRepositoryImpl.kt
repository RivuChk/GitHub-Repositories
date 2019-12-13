package dev.rivu.githubrepositories.data

import dev.rivu.githubrepositories.data.model.mapper.DataToDomainMapper
import dev.rivu.githubrepositories.data.store.TrendingProjectsDataStore
import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.repository.TrendingProjectRepository
import io.reactivex.Single

open class TrendingProjectsRepositoryImpl(
    private val cacheDataStore: TrendingProjectsDataStore,
    private val remoteDataStore: TrendingProjectsDataStore,
    private val mapper: DataToDomainMapper
) : TrendingProjectRepository {

    override fun getTrendingProjects(
        language: String,
        since: String
    ): Single<List<TrendingProject>> {
        //Didn't use the cacheDataStore, since cache isn't supported as of now
        return remoteDataStore.getTrendingProjects(language, since)
            .map { trendingProjectList ->
                trendingProjectList.map {
                    mapper.mapToDomain(it)
                }
            }
    }

}