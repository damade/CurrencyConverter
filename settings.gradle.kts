rootProject.name = "CurrencyConverter"
pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        gradlePluginPortal()
    }
}
include(
    ":app",
    ":core",
    ":core-android",
    ":libraries:remote",
    ":libraries:cache",
    ":config",
    ":lib_compose_core",
    ":lib_currency_search",
    ":lib_currency_flag",
    ":lint_playground",
    ":ft_currency",
    ":ft_home",
    ":navigation",
    ":libraries:testUtils",
)
