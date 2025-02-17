import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.currencyConverterPlugin : PluginDependencySpec
    get() = id("CurrencyConverterPlugin")

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = id("com.android.application")

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = id("com.android.library")

val PluginDependenciesSpec.javaLibrary: PluginDependencySpec
    get() = id("java-library")

val PluginDependenciesSpec.jvmLibrary: PluginDependencySpec
    get() = id("org.jetbrains.kotlin.jvm")

val PluginDependenciesSpec.apollo: PluginDependencySpec
    get() = id("com.apollographql.apollo3")

val PluginDependenciesSpec.daggerHilt: PluginDependencySpec
    get() = id("dagger.hilt.android.plugin")

val PluginDependenciesSpec.kotlinLibrary: PluginDependencySpec
    get() = id("kotlin-library")

val PluginDependenciesSpec.kotlinModule: PluginDependencySpec
    get() = id("kotlin")

val PluginDependenciesSpec.kotlinAndroidModule: PluginDependencySpec
    get() = kotlin("android")

val PluginDependenciesSpec.kotlinKaptModule: PluginDependencySpec
    get() = kotlin("kapt")

val PluginDependenciesSpec.safeArgs: PluginDependencySpec
    get() = id("androidx.navigation.safeargs.kotlin")

val PluginDependenciesSpec.parcelize: PluginDependencySpec
    get() = id("kotlin-parcelize")

fun RepositoryHandler.maven(url: String) {
    maven {
        setUrl(url)
    }
}

fun RepositoryHandler.mavenUnsecured(url: String) {
    maven {
        setUrl(url)
        isAllowInsecureProtocol = true
    }
}

fun RepositoryHandler.applyDefault() {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
    google()
    gradlePluginPortal()
}

fun DependencyHandler.implementAll(list: List<String>) {
    list.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addPlugins(list: List<String>) {
    list.forEach {
        add(ScriptHandler.CLASSPATH_CONFIGURATION, it)
    }
}

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.aP(dependencyNotation: String): Dependency? =
    add("annotationProcessor", dependencyNotation)
