package dev.rivu.githubrepositories

import android.app.Activity
import android.app.Application
import android.content.Context
import timber.log.Timber
import timber.log.Timber.DebugTree
import androidx.core.content.ContextCompat.getSystemService
import dev.rivu.githubrepositories.cache.injection.CacheModule
import dev.rivu.githubrepositories.injection.CoreComponent
import dev.rivu.githubrepositories.injection.CoreModule
import dev.rivu.githubrepositories.injection.DaggerCoreComponent
import dev.rivu.githubrepositories.remote.injection.RemoteModule
import java.io.File
import dev.rivu.githubrepositories.remote.BuildConfig as RemoteBuildConfig


open class GithubRepoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            //TODO: Production Log/Crash Reporting
        }

    }

    open fun getBaseUrl(): String = RemoteBuildConfig.BASE_URL

    fun cacheDir(): File = this.cacheDir

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .cacheModule(CacheModule())
            .build()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as GithubRepoApp).coreComponent
    }
}

fun Activity.coreComponent() = GithubRepoApp.coreComponent(this)