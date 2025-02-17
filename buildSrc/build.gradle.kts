plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradle)
    implementation(libs.gradle)
    implementation(libs.navigationSafeArgsPlugin)
    implementation(libs.daggerHiltPlugin)
    implementation(libs.apolloGraphQlPlugin)
}

gradlePlugin {
    plugins {
        register("CurrencyConverterPlugin") {
            id = "CurrencyConverterPlugin"
            implementationClass = "CurrencyConverterPlugin"
        }
    }
}
