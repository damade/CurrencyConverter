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
    compileOnly("com.android.tools.lint:lint-api:${lintVersion}")
    compileOnly("com.android.tools.lint:lint-checks:${lintVersion}")

    // Lint testing
    testImplementation("com.android.tools.lint:lint:${lintVersion}")
    testImplementation("com.android.tools.lint:lint-tests:${lintVersion}")
    testImplementation("junit:junit:4.13.1")
}