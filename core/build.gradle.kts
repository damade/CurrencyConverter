import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.RxJava
import Dependencies.Test
import ProjectLib.testUtils


plugins {
    javaLibrary
    jvmLibrary
    kotlin(kotlinKapt)
}
apply<CurrencyConverterPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation(project(testUtils))
    implementation(DI.hiltCore)
    implementation(Coroutines.core)
    RxJava.run {
        implementation(rxJavaCore)
        implementation(rxRelay)
    }

    kapt(DI.AnnotationProcessor.daggerHilt)

    Test.run {
        testImplementation(junit)
        testImplementation(truth)
        testImplementation(coroutinesTest)
        testImplementation(turbine)
    }
}
