plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.android.navigation.safeargs)
    alias(libs.plugins.hilt)
    alias(libs.plugins.currencyconverter.app.plugin)
    id("CurrencyConverterLibraryComposePlugin")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.damilola.navigation"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(projects.libComposeCore)

    implementation(projects.ftHome)
    implementation(projects.ftCurrency)

    implementation(libs.navigationFragmentKtx)
    implementation(libs.navigationUiKtx)

    implementation(libs.fragment)

    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.hilt.compiler)


    libs.run {
        testImplementation(junit)
        testImplementation(truth)
    }
}