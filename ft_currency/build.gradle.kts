import Dependencies.Compose
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.RxJava
import Dependencies.Test
import Dependencies.View
import ProjectLib.core
import ProjectLib.coreAndroid
import ProjectLib.libComposeCore
import ProjectLib.libCurrency
import ProjectLib.libCurrencySearch

plugins {
    androidLibrary
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
    sourceSets {
        getByName("androidTest") {
            assets.srcDirs(File(projectDir, "schemas"))
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompiler
    }

    namespace = "com.damilola.ft_currency"

    buildFeatures {
        compose = true
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(core))
    implementation(project(coreAndroid))
    implementation(project(libComposeCore))
    implementation(project(libCurrency))
    implementation(project(libCurrencySearch))
    implementAll(RxJava.components)

    implementation(DI.daggerHiltAndroid)

    androidTestImplementation(Test.compose)

    debugImplementation(Compose.composeUiPreview)
    debugImplementation(Compose.composeUiTestManifest)

    implementAll(Compose.components)
    implementAll(Coroutines.components)
    implementation(View.fragment)

    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(DI.AnnotationProcessor.androidxHiltCompiler)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockWebServer)
}
