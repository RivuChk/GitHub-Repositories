package dev.rivu.githubrepositories

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree
import androidx.core.content.ContextCompat.getSystemService



class GithubRepoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            //TODO: Production Log/Crash Reporting
        }

    }
}