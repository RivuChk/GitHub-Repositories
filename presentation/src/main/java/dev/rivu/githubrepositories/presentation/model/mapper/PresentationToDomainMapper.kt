package dev.rivu.githubrepositories.presentation.model.mapper

import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.domain.model.mapper.Mapper
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation


open class PresentationToDomainMapper : Mapper<TrendingProject, TrendingProjectPresentation> {
    override fun mapFromDomain(type: TrendingProject): TrendingProjectPresentation {
        return TrendingProjectPresentation(
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
                TrendingProjectPresentation.BuiltBy(
                    username = it.username,
                    href = it.href,
                    avatar = it.avatar
                )
            }
        )
    }

    override fun mapToDomain(type: TrendingProjectPresentation): TrendingProject {
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