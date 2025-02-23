plugins {
    androidLibrary
    kotlinAndroidModule
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.compose.compiler)
    currencyConverterPlugin
}

android {
    namespace = "com.damilola.ksp_usage"

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(projects.libComposeCore)

    debugImplementation(libs.composeUiPreview)
    debugImplementation(libs.composeUiTestManifest)

    implementation(libs.bundles.composeComponents)

    implementation(projects.kspPlayground.annotation)
    ksp(projects.kspPlayground.processor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}