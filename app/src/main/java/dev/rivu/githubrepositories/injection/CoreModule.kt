package dev.rivu.githubrepositories.injection

import dagger.Module
import dagger.Provides
import dev.rivu.githubrepositories.GithubRepoApp
import dev.rivu.githubrepositories.cache.injection.CacheModule
import dev.rivu.githubrepositories.remote.injection.RemoteModule
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [CacheModule::class])
class CoreModule(val app: GithubRepoApp) {
    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrl(): String = app.getBaseUrl()

    @Provides
    @Singleton
    fun cacheDir(): File = app.cacheDir()
}