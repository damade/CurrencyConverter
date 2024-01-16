import Dependencies.AndroidX
import Dependencies.Compose
import Dependencies.Test
import ProjectLib.core
import ProjectLib.coreAndroid

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
}

apply<CurrencyConverterPlugin>()

android {

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompiler
    }

    buildFeatures {
        compose = true
    }

    namespace = "com.damilola.lib_compose_core"
}


dependencies {
    implementation(project(core))
    implementation(project(coreAndroid))
    implementation(AndroidX.coreKtx)

    implementAll(Compose.components)
    androidTestImplementation(Test.compose)
    debugImplementation(Compose.composeUiPreview)
    debugImplementation(Compose.composeUiTestManifest)
}