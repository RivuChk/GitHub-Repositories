package dev.rivu.githubrepositories.trendingprojects

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dev.rivu.githubrepositories.R
import dev.rivu.githubrepositories.testutils.TestHelper
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestTrendingProjectsActivity {
    @get:Rule
    public val activityTestRule = ActivityTestRule(TrendingProjectsActivity::class.java)

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = TestHelper.setupMockServer()
    }

    @Test
    fun shouldShowLoadingInitially() {
        activityTestRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.shimmerLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}