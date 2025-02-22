plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    safeArgs
    daggerHilt
    currencyConverterPlugin
    alias(libs.plugins.compose.compiler) apply false
}

kapt {
    correctErrorTypes = true
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
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

    implementation(libs.bundles.composeComponents)

    debugImplementation(libs.composeUiPreview)
    debugImplementation(libs.composeUiTestManifest)

    implementation(libs.fragment)

    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.hilt.compiler)


    libs.run {
        testImplementation(junit)
        testImplementation(truth)
    }
}