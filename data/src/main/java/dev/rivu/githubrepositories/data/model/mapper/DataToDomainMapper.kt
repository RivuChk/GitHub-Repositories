package dev.rivu.githubrepositories.data.model.mapper

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.model.mapper.Mapper

open class DataToDomainMapper : Mapper<TrendingProject, TrendingProjectData> {
    override fun mapFromDomain(type: TrendingProject): TrendingProjectData {
        return TrendingProjectData(
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
                TrendingProjectData.BuiltBy(
                    username = it.username,
                    href = it.href,
                    avatar = it.avatar
                )
            }
        )
    }

    override fun mapToDomain(type: TrendingProjectData): TrendingProject {
        return TrendingProject(
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
                TrendingProject.BuiltBy(
                    username = it.username,
                    href = it.href,
                    avatar = it.avatar
                )
            }
        )
    }
}