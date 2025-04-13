import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.dsl.ScriptHandler

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
