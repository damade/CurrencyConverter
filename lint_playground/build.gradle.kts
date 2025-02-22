plugins {
    javaLibrary
    jvmLibrary
}

apply<CurrencyConverterPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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