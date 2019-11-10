package dev.rivu.githubrepositories.remote.model.mapper

import dev.rivu.githubrepositories.remote.factory.TrendingProjectsDataFactory
import dev.rivu.githubrepositories.remote.factory.TrendingProjectsResponseFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResponseToDataMapperTest {
    lateinit var mapper: ResponseToDataMapper

    @Before
    fun setup() {
        mapper = ResponseToDataMapper()
    }

    @Test
    fun `mapFromDomain should return same data`() {
        val data = TrendingProjectsResponseFactory.makeTrendingProject()
        val mappedData = mapper.mapToDomain(data)

        assertEquals(data.author, mappedData.author)
        assertEquals(data.name, mappedData.name)
        assertEquals(data.avatar, mappedData.avatar)
        assertEquals(data.url, mappedData.url)
        assertEquals(data.description, mappedData.description)
        assertEquals(data.language, mappedData.language)
        assertEquals(data.languageColor, mappedData.languageColor)
        assertEquals(data.stars, mappedData.stars)
        assertEquals(data.forks, mappedData.forks)
        assertEquals(data.currentPeriodStars, mappedData.currentPeriodStars)
        assertEquals(data.builtBy.size, mappedData.builtBy.size)
        for (i in data.builtBy.indices) {
            assertEquals(data.builtBy[i].avatar, mappedData.builtBy[i].avatar)
            assertEquals(data.builtBy[i].href, mappedData.builtBy[i].href)
            assertEquals(data.builtBy[i].username, mappedData.builtBy[i].username)
        }
    }

    @Test
    fun `mapToDomain should return same data`() {
        val data = TrendingProjectsDataFactory.makeTrendingProject()
        val mappedData = mapper.mapFromDomain(data)

        assertEquals(data.author, mappedData.author)
        assertEquals(data.name, mappedData.name)
        assertEquals(data.avatar, mappedData.avatar)
        assertEquals(data.url, mappedData.url)
        assertEquals(data.description, mappedData.description)
        assertEquals(data.language, mappedData.language)
        assertEquals(data.languageColor, mappedData.languageColor)
        assertEquals(data.stars, mappedData.stars)
        assertEquals(data.forks, mappedData.forks)
        assertEquals(data.currentPeriodStars, mappedData.currentPeriodStars)
        assertEquals(data.builtBy.size, mappedData.builtBy.size)
        for (i in data.builtBy.indices) {
            assertEquals(data.builtBy[i].avatar, mappedData.builtBy[i].avatar)
            assertEquals(data.builtBy[i].href, mappedData.builtBy[i].href)
            assertEquals(data.builtBy[i].username, mappedData.builtBy[i].username)
        }
    }
}