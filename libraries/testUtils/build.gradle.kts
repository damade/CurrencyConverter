plugins {
    kotlinModule
    currencyConverterPlugin
}

dependencies {
    api(libs.junit)
    api(libs.truth)
    api(libs.coroutinesTest)
    api(libs.turbine)
}
