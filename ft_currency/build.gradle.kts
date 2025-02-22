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
    sourceSets {
        getByName("androidTest") {
            assets.srcDirs(File(projectDir, "schemas"))
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    namespace = "com.damilola.ft_currency"

    buildFeatures {
        compose = true
    }
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

    debugImplementation(libs.composeUiPreview)
    debugImplementation(libs.composeUiTestManifest)

    implementation(libs.bundles.coroutinesComponents)
    implementation(libs.fragment)
    implementation(libs.bundles.composeComponents)

    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockWebServer)
}
