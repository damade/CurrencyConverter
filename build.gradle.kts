buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.kotlin.gradle)
        classpath(libs.gradle)
        classpath(libs.daggerHiltPlugin)
        classpath(libs.apolloGraphQlPlugin)
        classpath(libs.navigationSafeArgsPlugin)
    }
}

plugins {
    // Existing plugins
    id("com.android.application") version "8.12.0-alpha08" apply false
    id("com.android.library") version "8.12.0-alpha08" apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.navigation.safeargs) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.apollo) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.currencyconverter.custom.tasks)
}