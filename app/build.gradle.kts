import org.gradle.kotlin.dsl.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Config.targetSdkVersion)
    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/services/javax.annotation.processing.Processor")
        exclude("LICENSE.txt")
        exclude("META-INF/license/LICENSE.base64.txt")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/MANIFEST.MF")
    }
}

dependencies {

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.core)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidTestRunner)
    androidTestImplementation(Dependencies.Test.espresso)

}
