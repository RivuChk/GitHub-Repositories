package dev.rivu.githubrepositories.injection

import dagger.Component
import dev.rivu.githubrepositories.cache.injection.CacheModule
import dev.rivu.githubrepositories.data.source.TrendingProjectsCache
import dev.rivu.githubrepositories.data.source.TrendingProjectsRemote
import dev.rivu.githubrepositories.remote.injection.RemoteModule
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        fun coreModule(coreModule: CoreModule): Builder
        fun cacheModule(cacheModule: CacheModule): Builder
    }

    @Named("baseUrl")
    fun baseUrl(): String

    fun cacheDir(): File

    fun provideTrendingProjectsCache(): TrendingProjectsCache
}