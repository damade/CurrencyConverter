plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.currencyconverter.app.plugin)
    id("CurrencyConverterLibraryComposePlugin")
}

android {
    namespace = "com.damilola.lib_compose_core"
}


dependencies {
    implementation(projects.core)
    implementation(projects.coreAndroid)
    implementation(libs.androidx.core.ktx)


    androidTestImplementation(libs.composeUiTest)
}