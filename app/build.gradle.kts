plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.currencyconverter.app.plugin)
    alias(libs.plugins.currencyconverter.application.compose.plugin)
    alias(libs.plugins.android.navigation.safeargs)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.damilola.currencyconverter"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(projects.ftCurrency)
    implementation(projects.ftHome)
    implementation(projects.kspPlayground.kspUsage)
    implementation(projects.navigation)

    debugImplementation(libs.leakCanary)
    debugImplementation(libs.logger)

    androidTestImplementation(libs.composeUiTest)

    lintChecks(projects.lintPlayground)

    libs.run {
        implementation(androidx.core.ktx)
        implementation(navigationUiKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationDynamicFeatureKtx)
        implementation(multiDex)
        implementation(activity)
        implementation(activityKtx)
        implementation(lifeCycleCommon)
        implementation(liveData)
        implementation(runTime)
        implementation(viewModel)
        implementation(savedState)
        implementation(legacy)
    }

    libs.run {
        implementation(cardView)
        implementation(androidx.appcompat)
        implementation(fragment)
        implementation(swipeRefresh)
        implementation(lottieAnimation)
        implementation(pinView)
        implementation(cardNumberView)
        implementation(material)
        implementation(shimmerLayout)
        implementation(recyclerView)
    }

    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockWebServer)
}