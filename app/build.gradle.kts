import Dependencies.AndroidX
import Dependencies.Compose
import Dependencies.DI
import Dependencies.Performance
import Dependencies.Test
import Dependencies.View
import ProjectLib.core
import ProjectLib.coreAndroid
import ProjectLib.ftCurrency
import ProjectLib.ftHome
import ProjectLib.libCurrency
import ProjectLib.libCurrencySearch
import ProjectLib.navigation

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    safeArgs
    daggerHilt
}

apply<CurrencyConverterPlugin>()

kapt {
    correctErrorTypes = true
}
android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompiler
    }
    namespace = "com.damilola.currencyconverter"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(core))
    implementation(project(coreAndroid))
    implementation(project(libCurrencySearch))
    implementation(project(libCurrency))
    implementation(project(ftCurrency))
    implementation(project(ftHome))
    implementation(project(navigation))

    debugImplementation(Performance.leakCanary)
    debugImplementation(Performance.logger)

    androidTestImplementation(Test.compose)

    debugImplementation(Compose.composeUiPreview)
    debugImplementation(Compose.composeUiTestManifest)

    implementAll(Compose.components)

    implementAll(View.components)

    implementation(DI.daggerHiltAndroid)

    implementAll(AndroidX.components)

    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(DI.AnnotationProcessor.androidxHiltCompiler)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockWebServer)
}
