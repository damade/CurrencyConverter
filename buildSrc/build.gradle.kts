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

object PluginsDependencies {
    object Version {
        const val kotlin: String = "1.8.22"
        const val androidGradle: String = "8.3.2"
        const val navigation: String = "2.5.3"
        const val daggerHiltAndroid: String = "2.44"
        const val apolloGraphQl: String = "3.6.2"
    }

    const val kotlinPlugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradlePlugin: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgsPlugin: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHiltPlugin: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
    const val apolloGraphQlPlugin: String =
        "com.apollographql.apollo3:apollo-gradle-plugin:${Version.apolloGraphQl}"
}

dependencies {
    implementation(PluginsDependencies.kotlinPlugin)
    implementation(PluginsDependencies.androidGradlePlugin)
    implementation(PluginsDependencies.navigationSafeArgsPlugin)
    implementation(PluginsDependencies.daggerHiltPlugin)
    implementation(PluginsDependencies.apolloGraphQlPlugin)
}
