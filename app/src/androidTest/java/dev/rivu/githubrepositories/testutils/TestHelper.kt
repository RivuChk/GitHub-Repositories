package dev.rivu.githubrepositories.testutils

import android.content.Context
import android.util.Log
import androidx.annotation.RawRes
import androidx.test.platform.app.InstrumentationRegistry
import dev.rivu.githubrepositories.FakeGithubRepoApp
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


object TestHelper {
    fun getStringFromRaw(context: Context, @RawRes rawresId: Int): String {
        val stream = context.resources.openRawResource(rawresId)

        return stream.use { convertStreamToString(it) }
    }

    fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        for (line in reader.lines()) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }

    fun setupMockServer(mockResponse: MockResponse? = null): MockWebServer {
        val mockServer = MockWebServer()
        mockServer.start(8080)

        val baseURL = mockServer.url("/").toString()
        val testWeatherApp =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as FakeGithubRepoApp
        testWeatherApp.baseurl = baseURL

        if (null != mockResponse) {
            mockServer.enqueue(mockResponse)
        }

        Log.d("Test", "MockWebserver setup $baseURL")

        return mockServer
    }
}