import Dependencies.Cache
import Dependencies.DI
import Dependencies.RxJava
import Dependencies.Test
import ProjectLib.cache
import ProjectLib.core
import ProjectLib.coreAndroid
import ProjectLib.remote

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
    namespace = "com.damade.lib_currency"
}

dependencies {

    implementation(project(remote))
    implementation(project(cache))
    implementation(project(core))
    implementation(project(coreAndroid))
    implementAll(RxJava.components)

    implementation(DI.daggerHiltAndroid)
    kapt(DI.AnnotationProcessor.daggerHilt)

    testImplementation(Cache.room)
    testImplementation(Test.runner)
    testImplementation(Test.androidXTest)
    testImplementation(Test.robolectric)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockWebServer)
}
