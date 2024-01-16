import Dependencies.Performance

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
}

apply<CurrencyConverterPlugin>()

android {
    namespace = "com.damilola.config"
}

dependencies {
    debugImplementation(Performance.logger)
}
