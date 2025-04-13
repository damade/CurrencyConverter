plugins {
    id("com.android.library")
    kotlin("android")
    alias(libs.plugins.currencyconverter.app.plugin)
    alias(libs.plugins.google.ksp)
    id("CurrencyConverterLibraryComposePlugin")
}

android {
    namespace = "com.damilola.ksp_usage"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(projects.libComposeCore)

    implementation(projects.kspPlayground.annotation)
    ksp(projects.kspPlayground.processor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}