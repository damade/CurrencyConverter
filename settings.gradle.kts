pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "CurrencyConverter"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":app",
    ":config",
    ":core",
    ":core-android",
    ":ft_currency",
    ":ft_home",
    ":ksp_playground:annotation",
    ":ksp_playground:ksp_usage",
    ":ksp_playground:processor",
    ":lib_compose_core",
    ":lib_currency_search",
    ":lib_currency_flag",
    ":libraries:remote",
    ":libraries:cache",
    ":libraries:testUtils",
    ":lint_playground",
    ":navigation",
)
