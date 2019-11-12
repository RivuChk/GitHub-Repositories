object Versions {
    const val gradle = "3.5.2"
    const val kotlin = "1.3.50"
    const val dagger = "2.20"
    const val retrofit = "2.6.1"
    const val okHttp = "4.1.0"
    const val timber = "4.7.1"
    const val lifecycle = "2.0.0"
    const val glide = "4.9.0"
    const val shimmer = "0.5.0@aar"

    object AndroidX {
        const val appCompat = "1.0.2"
        const val core = "1.0.2"
        const val constraintLayout = "1.1.3"
        const val recyclerView = "1.0.0"
        const val cardView = "1.0.0"
        const val material = "1.0.0"
    }
    object Test {
        const val junit = "4.12"
        const val androidTestRunner = "1.2.0"
        const val espresso = "3.2.0"
        const val androidxTestExt = "1.1.1"
        const val hamcrest = "1.3"
        const val mockito = "1.10.19"
        const val mockitoKotlin = "1.5.0"
        const val androidXTestCore = "1.2.1-alpha02"
    }
    object Rx {
        const val rxJava2 = "2.2.11"
        const val rxAndroid2 = "2.1.1"
        const val rxBinding = "3.0.0"
    }
}

object Config {
    const val applicationId = "dev.rivu.githubrepositories"
    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1.0"
}

object BuildScript {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"
        const val cardView = "androidx.cardview:cardview:${Versions.AndroidX.cardView}"
        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"
    }
    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val androidTestRunner = "androidx.test:runner:${Versions.Test.androidTestRunner}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.Test.espresso}"
        const val androidxTestExt = "androidx.test.ext:junit:${Versions.Test.androidxTestExt}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.mockito}"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.Test.hamcrest}"
        const val testCore = "androidx.test:core:${Versions.Test.androidXTestCore}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.Test.androidXTestCore}"
        const val testRules = "androidx.test:rules:${Versions.Test.androidTestRunner}"
    }
    object Rx {
        const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.Rx.rxJava2}"
        const val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.rxAndroid2}"
    }
    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }
    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val rxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    }
    object AndroidArch {
        const val viewModelLiveData = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
        const val livedataRx = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifecycle}"
        const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    }
    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }
    object RxBinding {
        const val core = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.Rx.rxBinding}"
        const val recyclerview = "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.Rx.rxBinding}"
        const val swiperefreshlayout = "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.Rx.rxBinding}"
    }
}