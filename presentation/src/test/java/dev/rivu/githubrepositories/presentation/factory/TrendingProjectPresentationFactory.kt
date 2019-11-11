package dev.rivu.githubrepositories.presentation.factory

import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation

object TrendingProjectPresentationFactory {
    fun makeTrendingProject(): TrendingProjectPresentation {
        return TrendingProjectPresentation(
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

    fun makeBuiltBy(): TrendingProjectPresentation.BuiltBy {
        return TrendingProjectPresentation.BuiltBy(
            username = DataFactory.randomString(),
            href = DataFactory.randomString(),
            avatar = DataFactory.randomString()
        )
    }

    fun makeBuiltByList(count: Int = 10): List<TrendingProjectPresentation.BuiltBy> {
        val list = mutableListOf<TrendingProjectPresentation.BuiltBy>()
        repeat(count) {
            list.add(makeBuiltBy())
        }
        return list
    }

    fun makeTrendingProjectList(count: Int = 10): List<TrendingProjectPresentation> {
        val list = mutableListOf<TrendingProjectPresentation>()
        repeat(count) {
            list.add(makeTrendingProject())
        }
        return list
    }
}