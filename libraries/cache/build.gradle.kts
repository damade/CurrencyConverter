plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.currencyconverter.app.plugin)
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

android {
    namespace = "com.damilola.cache"
}

dependencies {
    implementation(projects.core)
    implementation(projects.config)
    debugImplementation(libs.logger)
    implementation(libs.androidx.core.ktx)
    implementation(libs.paging)
    implementation(libs.bundles.networkComponents)
    implementation(libs.daggerHiltAndroid)
    implementation(libs.bundles.cacheComponents)
    implementation(libs.bundles.coroutinesComponents)
    kapt(libs.daggerHiltCompiler)
    kapt(libs.androidx.room.compiler)
}