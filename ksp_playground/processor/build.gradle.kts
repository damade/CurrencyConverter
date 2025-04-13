plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.currencyconverter.app.plugin)
}

dependencies {
    implementation(projects.kspPlayground.annotation)
    implementation(libs.ksp)
    implementation(libs.kotlinPoet)
}