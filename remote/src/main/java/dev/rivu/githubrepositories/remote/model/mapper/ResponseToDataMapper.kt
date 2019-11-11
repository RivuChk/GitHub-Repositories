package dev.rivu.githubrepositories.remote.model.mapper

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.domain.model.mapper.Mapper
import dev.rivu.githubrepositories.remote.model.TrendingProjectsResponse

open class ResponseToDataMapper : Mapper<TrendingProjectData, TrendingProjectsResponse> {
    override fun mapFromDomain(type: TrendingProjectData): TrendingProjectsResponse {
        return TrendingProjectsResponse(
            author = type.author,
            name = type.name,
            avatar = type.avatar,
            url = type.url,
            description = type.description,
            language = type.language,
            languageColor = type.languageColor,
            stars = type.stars,
            forks = type.forks,
            currentPeriodStars = type.currentPeriodStars,
            builtBy = type.builtBy.map {
                TrendingProjectsResponse.BuiltBy(
                    username = it.username,
                    href = it.href,
                    avatar = it.avatar
                )
            }
        )
    }

    override fun mapToDomain(type: TrendingProjectsResponse): TrendingProjectData {
        return TrendingProjectData(
            author = type.author,
            name = type.name,
            avatar = type.avatar ?: "",
            url = type.url,
            description = type.description ?: "",
            language = type.language ?: "",
            languageColor = type.languageColor ?: "",
            stars = type.stars,
            forks = type.forks,
            currentPeriodStars = type.currentPeriodStars,
            builtBy = type.builtBy?.map {
                TrendingProjectData.BuiltBy(
                    username = it.username,
                    href = it.href,
                    avatar = it.avatar
                )
            } ?: emptyList()
        )
    }
}