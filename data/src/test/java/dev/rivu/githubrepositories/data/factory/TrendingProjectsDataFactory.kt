package dev.rivu.githubrepositories.data.factory

import dev.rivu.githubrepositories.data.model.TrendingProjectData
import dev.rivu.githubrepositories.domain.model.TrendingProject

object TrendingProjectsDataFactory {
    fun makeTrendingProject(): TrendingProjectData {
        return TrendingProjectData(
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
            builtBy = makeBuiltByList(DataFactory.randomInt(1, 10))
        )
    }

    fun makeBuiltBy(): TrendingProjectData.BuiltBy {
        return TrendingProjectData.BuiltBy(
            username = DataFactory.randomString(),
            href = DataFactory.randomString(),
            avatar = DataFactory.randomString()
        )
    }

    fun makeBuiltByList(count: Int = 10): List<TrendingProjectData.BuiltBy> {
        val list = mutableListOf<TrendingProjectData.BuiltBy>()
        repeat(count) {
            list.add(makeBuiltBy())
        }
        return list
    }

    fun makeTrendingProjectList(count: Int = 10): List<TrendingProjectData> {
        val list = mutableListOf<TrendingProjectData>()
        repeat(count) {
            list.add(makeTrendingProject())
        }
        return list
    }
}