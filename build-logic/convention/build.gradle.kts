import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.damilola.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.gradle)
    compileOnly(libs.daggerHiltPlugin)
    compileOnly(libs.apolloGraphQlPlugin)
}

gradlePlugin {
    plugins {
        register("CurrencyConverterPlugin") {
            id = "CurrencyConverterPlugin"
            implementationClass = "CurrencyConverterPlugin"
        }
        register("CurrencyConverterCustomTask") {
            id = "CurrencyConverterCustomTask"
            implementationClass = "CurrencyConverterCustomTasks"
        }
        register("CurrencyConverterLibraryComposePlugin") {
            id = "CurrencyConverterLibraryComposePlugin"
            implementationClass = "CurrencyConverterLibraryComposePlugin"
        }
        register("CurrencyConverterApplicationComposePlugin") {
            id = "CurrencyConverterApplicationComposePlugin"
            implementationClass = "CurrencyConverterApplicationComposePlugin"
        }
    }
}
