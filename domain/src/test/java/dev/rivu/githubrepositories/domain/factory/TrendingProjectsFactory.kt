package dev.rivu.githubrepositories.domain.factory

import dev.rivu.githubrepositories.domain.model.TrendingProject

object TrendingProjectsFactory {
    fun makeTrendingProject(): TrendingProject {
        return TrendingProject(
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
            builtBy = listOf()
        )
    }

    fun makeBuiltBy(): TrendingProject.BuiltBy {
        return TrendingProject.BuiltBy(
            username = DataFactory.randomString(),
            href = DataFactory.randomString(),
            avatar = DataFactory.randomString()
        )
    }

    fun makeBuiltByList(count: Int = 10): List<TrendingProject.BuiltBy> {
        val list = mutableListOf<TrendingProject.BuiltBy>()
        repeat(count) {
            list.add(makeBuiltBy())
        }
        return list
    }

    fun makeTrendingProjectList(count: Int = 10): List<TrendingProject> {
        val list = mutableListOf<TrendingProject>()
        repeat(count) {
            list.add(makeTrendingProject())
        }
        return list
    }
}