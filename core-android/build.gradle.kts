plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.currencyconverter.app.plugin)
}

kapt {
    correctErrorTypes = true
}
android {
    namespace = "com.damilola.core_android"
}

dependencies {
    implementation(projects.core)
    implementation(projects.config)

    libs.run {
        implementation(lifeCycleCommon)
        implementation(runTime)
        implementation(androidx.core.ktx)
        implementation(activity)
    }

    libs.run {
        implementation(fragment)
        implementation(swipeRefresh)
        implementation(shimmerLayout)
        implementation(lottieAnimation)
        implementation(androidx.appcompat)
        implementation(recyclerView)
        implementation(material)
    }

    implementation(libs.glideLoader)
    implementation(libs.circleImageView)
    implementation(libs.daggerHiltAndroid)
    implementation(libs.moshi)
    implementation(libs.coreCoroutine)
    implementation(libs.logger)

    implementation(libs.bundles.networkComponents)
    implementation(libs.bundles.rxjavaComponents)

    kapt(libs.daggerHiltCompiler)
    kapt(libs.glideCompiler)

    debugImplementation(libs.leakCanary)
    debugImplementation(libs.logger)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockWebServer)
}
