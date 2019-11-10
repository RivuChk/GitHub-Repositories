package dev.rivu.githubrepositories.remote.service

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

class TrendingServiceFactory(val baseUrl: String, val cacheDir: File) {

    fun makeApodService(): TrendingService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(),
            makeCache()
        )
        return makeApodService(okHttpClient, makeGson())
    }

    private fun makeApodService(okHttpClient: OkHttpClient, gson: Gson): TrendingService {
        return makeRetrofit(okHttpClient, gson).create(TrendingService::class.java)
    }

    private fun makeRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeCache(): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong() //5MB Cache Size
        return Cache(cacheDir, cacheSize)
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(httpLogger)
    }

    private val httpLogger: HttpLoggingInterceptor.Logger by lazy {
        object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d("HTTP::TrendingService:: $message")
            }
        }
    }
}