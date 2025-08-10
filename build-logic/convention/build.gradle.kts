import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl` version "6.2.0"
}

group = "com.damilola.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
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
