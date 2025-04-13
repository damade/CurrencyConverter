plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.currencyconverter.app.plugin)
}

dependencies {
    implementation(projects.libraries.testUtils)
    implementation(libs.coreCoroutine)
    libs.run {
        implementation(rxJavaCore)
        implementation(rxRelay)
    }
    libs.run {
        testImplementation(junit)
        testImplementation(junitParams)
        testImplementation(truth)
        testImplementation(coroutinesTest)
        testImplementation(turbine)
    }
}
