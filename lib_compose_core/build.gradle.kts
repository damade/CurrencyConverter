plugins {
    androidLibrary
    kotlinAndroidModule
    kotlinKaptModule
    currencyConverterPlugin
    alias(libs.plugins.compose.compiler)
}

android {

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