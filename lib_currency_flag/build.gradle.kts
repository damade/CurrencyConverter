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
    namespace = "com.damade.lib_currency"
}

dependencies {
    implementation(projects.libraries.remote)
    implementation(projects.libraries.cache)
    implementation(projects.coreAndroid)
    implementation(projects.core)
    implementation(libs.bundles.rxjavaComponents)

    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltCompiler)

    testImplementation(libs.room)
    testImplementation(libs.runner)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.robolectric)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockWebServer)
}
