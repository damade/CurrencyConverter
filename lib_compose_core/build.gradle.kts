plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    currencyConverterPlugin
}

android {
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    buildFeatures {
        compose = true
    }

    namespace = "com.damilola.lib_compose_core"
}


dependencies {
    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(libs.androidx.core.ktx)

    implementation(libs.bundles.composeComponents)
    debugImplementation(libs.composeUiPreview)
    debugImplementation(libs.composeUiTestManifest)
    androidTestImplementation(libs.composeUiTest)
}