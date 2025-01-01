import Dependencies.AndroidX
import Dependencies.Cache
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Network
import Dependencies.Performance
import ProjectLib.config
import ProjectLib.core

plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    daggerHilt
}

apply<CurrencyConverterPlugin>()

kapt {
    correctErrorTypes = true
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

android {
    namespace = "com.damilola.cache"
}

dependencies {
    implementation(project(core))
    implementation(project(config))
    debugImplementation(Performance.logger)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.paging)
    implementAll(Network.components)
    implementation(DI.daggerHiltAndroid)
    implementAll(Cache.components)
    implementAll(Coroutines.components)
    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(Cache.AnnotationProcessor.room)
}