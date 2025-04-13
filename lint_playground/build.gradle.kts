plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.currencyconverter.app.plugin)
}

dependencies {
    // Lint
    compileOnly(libs.lint.api)
    compileOnly(libs.lint.checks)

    // Lint testing
    testImplementation(libs.lint)
    testImplementation(libs.lint.tests)
    testImplementation(libs.junit)
}