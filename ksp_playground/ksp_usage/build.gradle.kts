plugins {
    androidLibrary
    kotlinAndroidModule
    id("com.google.devtools.ksp") version "1.9.21-1.0.16"
}

apply<CurrencyConverterPlugin>()

android {
    namespace = "com.damilola.ksp_usage"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(project(ProjectLib.kspPlaygroundAnnotation))
    ksp(project(ProjectLib.kspPlaygroundProcessor))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}