plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    safeArgs
    daggerHilt
    currencyConverterPlugin
}

kapt {
    correctErrorTypes = true
}
android {
    namespace = "com.damilola.ft_home"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(projects.libCurrencySearch)

    debugImplementation(libs.leakCanary)
    debugImplementation(libs.logger)


    with(libs){
        implementation(lifeCycleCommon)
        implementation(runTime)
        implementation(androidx.core.ktx)
        implementation(activityKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(viewModel)
        implementation(liveData)
        implementation(runTime)

        implementation(fragment)
        implementation(swipeRefresh)
        implementation(shimmerLayout)
        implementation(lottieAnimation)
        implementation(androidx.appcompat)
        implementation(recyclerView)
        implementation(material)
    }

    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}
