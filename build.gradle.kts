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
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library") version "8.9.1" apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.navigation.safeargs) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.apollo) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.currencyconverter.custom.tasks)
}

//tasks.register<GenerateFeatureModuleTask>("generateFeatureModule")

// Tasks for automatically installing git-hooks and making it executable also.
//tasks.register("installGitHook", Copy::class) {
//    from(file("$rootDir/scripts/pre-push"))
//    into(file("$rootDir/.git/hooks"))
//    fileMode = 0b0111101101 // -rwxr-xr-x
//}
//
//tasks.create(name = "gitExecutableHooks") {
//    doLast {
//        Runtime.getRuntime().exec("chmod -R +x .git/hooks/")
//    }
//}
//tasks.getByPath("gitExecutableHooks").dependsOn(tasks.named("installGitHook"))
//tasks.getByPath(":app:clean").dependsOn(tasks.named("gitExecutableHooks"))