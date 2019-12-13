package dev.rivu.githubrepositories.trendingprojects

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dev.rivu.githubrepositories.R
import dev.rivu.githubrepositories.testutils.RecyclerViewItemCountAssertion.Companion.withItemCount
import dev.rivu.githubrepositories.testutils.TestHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
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

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldShowLoadingInitially() {
        activityTestRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.shimmerLayout))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldShowResultView() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(
                    TestHelper.getStringFromRaw(
                        InstrumentationRegistry.getInstrumentation().targetContext,
                        R.raw.test_result
                    )
                )
        )

        activityTestRule.launchActivity(Intent())

        Espresso.onView(withId(R.id.swipeRefresh))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rvTrendingProjects)).check(withItemCount(3))
    }

    @Test
    fun shouldExpandOnClick() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(
                    TestHelper.getStringFromRaw(
                        InstrumentationRegistry.getInstrumentation().targetContext,
                        R.raw.test_result
                    )
                )
        )

        activityTestRule.launchActivity(Intent())

        Espresso.onView(withId(R.id.rvTrendingProjects))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TrendingProjectListAdapter.ViewHolder>(
                    1,
                    click()
                )
            )

        Espresso.onView(withText("Container Runtime Sandbox"))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkSortingMenuDisplayed() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(
                    TestHelper.getStringFromRaw(
                        InstrumentationRegistry.getInstrumentation().targetContext,
                        R.raw.test_result
                    )
                )
        )

        activityTestRule.launchActivity(Intent())

        Espresso.onView(withId(R.id.ivMenu))
            .perform(click())

        Espresso.onView(withId(R.id.tvMenuStars))
            .inRoot(isPlatformPopup())
            .check(matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvMenuName))
            .inRoot(isPlatformPopup())
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkErrorIsDisplayed() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        activityTestRule.launchActivity(Intent())

        Espresso.onView(withId(R.id.errorLayout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkLoadingOnRetryClick() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        activityTestRule.launchActivity(Intent())

        Espresso.onView(withId(R.id.errorLayout))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnRetry))
            .perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.shimmerLayout))
            .check(matches(ViewMatchers.isDisplayed()))
    }

}