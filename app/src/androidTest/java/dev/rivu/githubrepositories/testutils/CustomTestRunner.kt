package dev.rivu.githubrepositories.testutils

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.test.runner.AndroidJUnitRunner
import dev.rivu.githubrepositories.FakeGithubRepoApp

class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, FakeGithubRepoApp::class.java.name, context)
    }

    override fun onCreate(arguments: Bundle?) {

        super.onCreate(arguments)
    }
}