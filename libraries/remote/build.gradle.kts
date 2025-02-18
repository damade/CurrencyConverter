plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    daggerHilt
    apollo
    currencyConverterPlugin
}

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
    implementation(projects.core)
    implementation(projects.config)
    debugImplementation(libs.logger)
    implementation(libs.androidx.core.ktx)
    implementation(libs.daggerHiltAndroid)

    implementation(libs.bundles.networkComponents)
    implementation(libs.rxJavaCore)
    implementation(libs.bundles.apolloGraphqlComponents)
    implementation(libs.bundles.coroutinesComponents)
    implementation(libs.logger)
    kapt(libs.daggerHiltCompiler)
    kapt(libs.moshi.compiler)
}
