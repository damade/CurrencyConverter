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
import ProjectLib.kspPlaygroundUsage
import ProjectLib.navigation
import java.util.Locale

plugins {
    androidApplication
    kotlinAndroidModule
    kotlinKaptModule
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
        kotlinCompilerExtensionVersion = Compose.Version.composeCompiler
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
    implementation(project(ftCurrency))
    implementation(project(ftHome))
    implementation(project(kspPlaygroundUsage))
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