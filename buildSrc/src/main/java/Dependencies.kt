object Versions {
    const val gradle = "3.5.2"
    const val kotlin = "1.3.50"
    object AndroidX {
        const val appCompat = "1.0.2"
        const val core = "1.0.2"
    }
    object Test {
        const val junit = "4.12"
        const val androidTestRunner = "1.2.0"
        const val espresso = "3.2.0"
        const val androidxTestExt = "1.1.1"
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
    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
    }
    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val androidTestRunner = "androidx.test:runner:${Versions.Test.androidTestRunner}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val androidxTestExt = "androidx.test.ext:junit:${Versions.Test.androidxTestExt}"
    }
}