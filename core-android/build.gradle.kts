import Dependencies.AndroidX
import Dependencies.Compose
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Image
import Dependencies.Network
import Dependencies.Performance
import Dependencies.RxJava
import Dependencies.Test
import Dependencies.View
import ProjectLib.config
import ProjectLib.core

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
}

apply<CurrencyConverterPlugin>()

kapt {
    correctErrorTypes = true
}
android {
    namespace = "com.damilola.core_android"
}

dependencies {

    implementation(project(core))
    implementation(project(config))
    implementation(AndroidX.lifeCycleCommon)
    implementation(AndroidX.runTime)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.activity)

    View.run {
        implementation(fragment)
        implementation(swipeRefresh)
        implementation(shimmerLayout)
        implementation(lottieAnimation)
        implementation(appCompat)
        implementation(recyclerView)
        implementation(materialComponent)
    }
    implementation(Image.glideLoader)
    implementation(Image.circleImageView)
    implementation(DI.daggerHiltAndroid)
    implementation(Network.moshi)
    implementation(Coroutines.core)
    implementation(Performance.logger)
    implementAll(RxJava.components)
    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(Image.glideCompiler)

    debugImplementation(Performance.leakCanary)
    debugImplementation(Performance.logger)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockWebServer)
}
