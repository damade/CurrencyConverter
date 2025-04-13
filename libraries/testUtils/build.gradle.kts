plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.currencyconverter.app.plugin)
}

dependencies {
    api(libs.junit)
    api(libs.truth)
    api(libs.coroutinesTest)
    api(libs.turbine)
}
