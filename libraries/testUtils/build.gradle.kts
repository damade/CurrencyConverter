import Dependencies.Test

plugins {
    kotlinModule
}

apply<CurrencyConverterPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(Test.junit)
    api(Test.truth)
    api(Test.coroutinesTest)
    api(Test.turbine)
}
