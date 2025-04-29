plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.currencyconverter.app.plugin)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.navigation.safeargs)
    alias(libs.plugins.currencyconverter.library.compose.plugin)
}

kapt {
    correctErrorTypes = true
}

android {
    sourceSets {
        getByName("androidTest") {
            assets.srcDirs(File(projectDir, "schemas"))
        }
    }

    namespace = "com.damilola.ft_currency"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(projects.libComposeCore)
    implementation(projects.libCurrencyFlag)
    implementation(projects.libCurrencySearch)
    implementation(libs.bundles.rxjavaComponents)

    implementation(libs.bundles.coroutinesComponents)
    implementation(libs.compose.navigation)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.fragment)

    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.hilt.compiler)

    lintChecks(projects.lintPlayground)

    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.truth)
    testImplementation(libs.junit)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockWebServer)
}
