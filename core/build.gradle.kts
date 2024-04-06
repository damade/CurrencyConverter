import Dependencies.Coroutines
import Dependencies.RxJava
import Dependencies.Test
import ProjectLib.testUtils


plugins {
    kotlinModule
}

apply<CurrencyConverterPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(testUtils))
    implementation(Coroutines.core)
    RxJava.run {
        implementation(rxJavaCore)
        implementation(rxRelay)
    }
    Test.run {
        testImplementation(junit)
        testImplementation(truth)
        testImplementation(coroutinesTest)
        testImplementation(turbine)
    }
}
