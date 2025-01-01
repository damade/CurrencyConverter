import Dependencies.Playground

plugins {
    kotlinModule
}

apply<CurrencyConverterPlugin>()

dependencies {
    implementation(project(ProjectLib.kspPlaygroundAnnotation))
    implementation(Playground.ksp)
    implementation(Playground.kotlinPoet)
}