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

        buildConfigField("String", "BASE_URL", "${properties["BASE_URL"]}")
    }

}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependencies.kotlin)

    //Rx
    implementation(Dependencies.Rx.rxJava2)
    implementation(Dependencies.Rx.rxAndroid2)

    //Retrofit
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.rxJava2Adapter)
    implementation(Dependencies.Network.gsonConverter)

    //Dagger
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)

    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.mockitoKotlin)
    testImplementation(Dependencies.Test.hamcrest)
    testImplementation(Dependencies.Test.junit)
}
