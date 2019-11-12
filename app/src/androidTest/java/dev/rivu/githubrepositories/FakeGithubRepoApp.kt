package dev.rivu.githubrepositories

import android.content.res.Configuration
import androidx.test.platform.app.InstrumentationRegistry
import dev.rivu.githubrepositories.testutils.TestHelper
import okhttp3.mockwebserver.MockWebServer

class FakeGithubRepoApp : GithubRepoApp() {

    var baseurl: String = "http://localhost:8080"

    override fun getBaseUrl(): String {
        return baseurl
    }
}