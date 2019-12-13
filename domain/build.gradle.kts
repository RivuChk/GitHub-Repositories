plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Config.targetSdkVersion)


    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

}

dependencies {
    implementation(Dependencies.kotlin)

    //Rx
    implementation(Dependencies.Rx.rxJava2)
    implementation(Dependencies.Rx.rxAndroid2)

    //Dagger
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)

    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.mockitoKotlin)
    testImplementation(Dependencies.Test.hamcrest)
    testImplementation(Dependencies.Test.junit)

    api(Dependencies.timber)
}