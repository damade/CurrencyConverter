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
    val lintVersion = "31.5.2"

    // Lint
    compileOnly(libs.lint.api)
    compileOnly(libs.lint.checks)

    // Lint testing
    testImplementation(libs.lint)
    testImplementation(libs.lint.tests)
    testImplementation("junit:junit:4.13.1")
}