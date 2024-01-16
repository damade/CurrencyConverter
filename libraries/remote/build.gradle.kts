import BuildType.Companion.DEBUG
import BuildType.Companion.RELEASE
import Dependencies.AndroidX
import Dependencies.ApolloGraphQl
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Network
import Dependencies.Performance
import Dependencies.RxJava
import ProjectLib.config
import ProjectLib.core

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
    apollo
}

apply<CurrencyConverterPlugin>()

kapt {
    correctErrorTypes = true
}
apollo {
    useVersion2Compat()
}
android {
    namespace = "com.damilola.remote"
}

dependencies {

    implementation(project(core))
    implementation(project(config))
    debugImplementation(Performance.logger)
    implementation(AndroidX.coreKtx)
    implementation(DI.daggerHiltAndroid)
    implementAll(Network.components)
    implementation(RxJava.rxJavaCore)
    implementAll(ApolloGraphQl.components)
    implementAll(Coroutines.components)
    implementation(Performance.logger)
    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(Network.AnnotationProcessor.moshi)
}
