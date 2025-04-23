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

tasks.withType(Jar::class.java).configureEach {
    manifest.attributes["Lint-Registry-v2"] = "com.damilola.lint_playground.LintIssueRegistry"
}