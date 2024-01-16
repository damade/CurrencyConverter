import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Performance
import Dependencies.Test
import Dependencies.View
import ProjectLib.core
import ProjectLib.coreAndroid
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
    namespace = "com.damilola.ft_home"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(core))
    implementation(project(coreAndroid))
    implementation(project(libCurrencySearch))

    debugImplementation(Performance.leakCanary)
    debugImplementation(Performance.logger)

    with(AndroidX){
        implementation(lifeCycleCommon)
        implementation(runTime)
        implementation(coreKtx)
        implementation(activityKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(viewModel)
        implementation(liveData)
        implementation(runTime)
    }

    View.run {
        implementation(fragment)
        implementation(swipeRefresh)
        implementation(shimmerLayout)
        implementation(lottieAnimation)
        implementation(appCompat)
        implementation(recyclerView)
        implementation(materialComponent)
    }

    implementation(DI.daggerHiltAndroid)

    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(DI.AnnotationProcessor.androidxHiltCompiler)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
}
