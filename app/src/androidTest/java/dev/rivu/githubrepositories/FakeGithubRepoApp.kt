package dev.rivu.githubrepositories

import android.content.res.Configuration
import dev.rivu.githubrepositories.testutils.TestHelper

class FakeGithubRepoApp : GithubRepoApp() {

    var baseurl: String = "http://localhost:8080"

    override fun getBaseUrl(): String {
        return baseurl
    }
}