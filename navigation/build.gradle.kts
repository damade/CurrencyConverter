import Dependencies.AndroidX
import Dependencies.Compose
import Dependencies.DI
import Dependencies.Test
import Dependencies.View
import ProjectLib.core
import ProjectLib.coreAndroid
import ProjectLib.ftHome
import ProjectLib.ftCurrency
import ProjectLib.libComposeCore

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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.Version.composeCompiler
    }
    namespace = "com.damilola.navigation"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(core))
    implementation(project(coreAndroid))
    implementation(project(libComposeCore))

    implementation(project(ftHome))
    implementation(project(ftCurrency))

    implementation(AndroidX.navigationFragmentKtx)
    implementation(AndroidX.navigationUiKtx)

    implementAll(Compose.components)

    debugImplementation(Compose.composeUiPreview)
    debugImplementation(Compose.composeUiTestManifest)

    implementation(View.fragment)

    implementation(DI.daggerHiltAndroid)

    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(DI.AnnotationProcessor.androidxHiltCompiler)


    Test.run {
        testImplementation(junit)
        testImplementation(truth)
    }


}