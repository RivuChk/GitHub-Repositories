import org.gradle.kotlin.dsl.*

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

        consumerProguardFile("consumer-rules.pro")

    }

}

dependencies {
    implementation(project(":domain"))

    implementation(Dependencies.kotlin)

    //Rx
    implementation(Dependencies.Rx.rxJava2)
    implementation(Dependencies.Rx.rxAndroid2)

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.core)

    //Arch
    implementation(Dependencies.AndroidArch.viewModelLiveData)
    implementation(Dependencies.AndroidArch.lifecycleRuntime)
    implementation(Dependencies.AndroidArch.livedataRx)

    //Dagger
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)

    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.mockitoKotlin)
    testImplementation(Dependencies.Test.hamcrest)
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.AndroidArch.lifecycleTesting)
}
