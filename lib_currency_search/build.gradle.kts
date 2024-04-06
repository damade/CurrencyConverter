import Dependencies.Cache
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Network
import Dependencies.Test
import ProjectLib.cache
import ProjectLib.core
import ProjectLib.coreAndroid
import ProjectLib.remote
import ProjectLib.testUtils

plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    daggerHilt
}

apply<CurrencyConverterPlugin>()

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.damade.lib_currency_search"
}

dependencies {

    implementation(project(remote))
    implementation(project(cache))
    implementation(project(core))
    implementation(project(coreAndroid))
    implementation(project(testUtils))
    implementation(Coroutines.core)
    implementation(Network.retrofit)
    implementation(Network.retrofitMoshi)

    implementation(DI.daggerHiltAndroid)
    kapt(DI.AnnotationProcessor.daggerHilt)

    testImplementation(Network.moshi)
    testImplementation(Network.retrofitMoshi)
    testImplementation(Cache.room)

    testImplementation(Test.runner)
    testImplementation(Test.androidXTest)
    testImplementation(Test.robolectric)

    Test.run {
        testImplementation(junit)
        testImplementation(truth)
        testImplementation(coroutinesTest)
        testImplementation(mockWebServer)
        testImplementation(turbine)
    }

}
