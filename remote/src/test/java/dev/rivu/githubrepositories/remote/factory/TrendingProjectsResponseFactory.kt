package dev.rivu.githubrepositories.remote.factory

import dev.rivu.githubrepositories.domain.model.TrendingProject
import dev.rivu.githubrepositories.remote.model.TrendingProjectsResponse

object TrendingProjectsResponseFactory {
    fun makeTrendingProject(): TrendingProjectsResponse {
        return TrendingProjectsResponse(
            author = DataFactory.randomString(),
            name = DataFactory.randomString(),
            avatar = DataFactory.randomString(),
            url = DataFactory.randomString(),
            description = DataFactory.randomString(),
            language = DataFactory.randomString(),
            languageColor = DataFactory.randomString(),
            stars = DataFactory.randomInt(),
            forks = DataFactory.randomInt(),
            currentPeriodStars = DataFactory.randomInt(),
            builtBy = makeBuiltByList()
        )
    }

    fun makeBuiltBy(): TrendingProjectsResponse.BuiltBy {
        return TrendingProjectsResponse.BuiltBy(
            username = DataFactory.randomString(),
            href = DataFactory.randomString(),
            avatar = DataFactory.randomString()
        )
    }

    fun makeBuiltByList(count: Int = 10): List<TrendingProjectsResponse.BuiltBy> {
        val list = mutableListOf<TrendingProjectsResponse.BuiltBy>()
        repeat(count) {
            list.add(makeBuiltBy())
        }
        return list
    }

    fun makeTrendingProjectList(count: Int = 10): List<TrendingProjectsResponse> {
        val list = mutableListOf<TrendingProjectsResponse>()
        repeat(count) {
            list.add(makeTrendingProject())
        }
        return list
    }
}