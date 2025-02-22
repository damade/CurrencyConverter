plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    daggerHilt
    currencyConverterPlugin
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.damade.lib_currency_search"
}

dependencies {

    implementation(projects.libraries.remote)
    implementation(projects.libraries.cache)
    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(projects.libraries.testUtils)
    implementation(libs.coreCoroutine)

    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltCompiler)

    implementation(libs.retrofit)
    implementation(libs.retrofitMoshi)
    testImplementation(libs.moshi)
    testImplementation(libs.retrofitMoshi)
    testImplementation(libs.room)

    libs.run {
        testImplementation(runner)
        testImplementation(androidx.junit)
        testImplementation(robolectric)
        testImplementation(junit)
        testImplementation(truth)
        testImplementation(coroutinesTest)
        testImplementation(mockWebServer)
        testImplementation(turbine)
    }

}
