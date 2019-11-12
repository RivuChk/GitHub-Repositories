package dev.rivu.githubrepositories.testutils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dev.rivu.githubrepositories.FakeGithubRepoApp

public open class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, FakeGithubRepoApp::class.java.name, context)
    }
}