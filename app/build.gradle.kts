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

        testInstrumentationRunner = "dev.rivu.githubrepositories.testutils.CustomTestRunner"
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

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":cache"))
    implementation(project(":remote"))
    implementation(project(":presentation"))

    //Rx
    implementation(Dependencies.Rx.rxJava2)
    implementation(Dependencies.Rx.rxAndroid2)

    implementation(Dependencies.kotlin)

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.recyclerView)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.cardView)

    //Arch
    implementation(Dependencies.AndroidArch.viewModelLiveData)
    implementation(Dependencies.AndroidArch.lifecycleRuntime)
    implementation(Dependencies.AndroidArch.livedataRx)

    //Dagger
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)

    //Shimmer
    implementation(Dependencies.shimmer)

    //Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.glideCompiler)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidTestRunner)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.espressoContrib)
    androidTestImplementation(Dependencies.Network.mockWebServer)
    androidTestImplementation(Dependencies.Test.androidxTestExt)
    androidTestImplementation(Dependencies.Test.testCore)
    androidTestImplementation(Dependencies.Test.testCoreKtx)
    androidTestImplementation(Dependencies.Test.testRules)

    //RxBinding
    implementation(Dependencies.RxBinding.core)
    implementation(Dependencies.RxBinding.recyclerview)
    implementation(Dependencies.RxBinding.swiperefreshlayout)

}
