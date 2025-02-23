plugins {
    javaLibrary
    jvmLibrary
    currencyConverterPlugin
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