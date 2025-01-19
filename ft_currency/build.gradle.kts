import Dependencies.Compose
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.RxJava
import Dependencies.Test
import Dependencies.View
import ProjectLib.core
import ProjectLib.coreAndroid
import ProjectLib.libComposeCore
import ProjectLib.libCurrencyFlag
import ProjectLib.libCurrencySearch

plugins {
    androidLibrary
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
    sourceSets {
        getByName("androidTest") {
            assets.srcDirs(File(projectDir, "schemas"))
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.Version.composeCompiler
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
    implementation(project(libCurrencyFlag))
    implementation(project(libCurrencySearch))
    implementAll(RxJava.components)

    debugImplementation(Compose.composeUiPreview)
    debugImplementation(Compose.composeUiTestManifest)

    implementAll(Compose.components)
    implementAll(Coroutines.components)
    implementation(View.fragment)

    implementation(DI.daggerHiltAndroid)
    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(DI.AnnotationProcessor.androidxHiltCompiler)

    androidTestImplementation(Test.compose)
    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockWebServer)
}
