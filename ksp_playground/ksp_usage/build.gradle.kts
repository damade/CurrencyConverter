plugins {
    androidLibrary
    kotlinAndroidModule
    id("com.google.devtools.ksp") version "1.9.21-1.0.16"
    alias(libs.plugins.compose.compiler) apply false
    currencyConverterPlugin
}

android {
    namespace = "com.damilola.ksp_usage"

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

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