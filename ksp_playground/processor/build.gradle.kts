plugins {
    kotlinModule
    currencyConverterPlugin
}

dependencies {
    implementation(projects.kspPlayground.annotation)
    implementation(libs.ksp)
    implementation(libs.kotlinPoet)
}