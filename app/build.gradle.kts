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

// build.gradle.kts (Module :app)
tasks.register("moveTemplateToFeature") {
    val rootFolderName = "projectTemplate"

    val featureName = project.findProperty("featureName") as String?
        ?: error("Feature name is required. Use -PfeatureName=<name> to specify it.")

    val templateName = project.findProperty("templateName") as String?
        ?: error("Template name is required. Use -PtemplateName=<name> to specify it.")

//    val packageName = project.findProperty("packageName") as String?
//        ?: error("Template name is required. Use -PpackageName=<name> to specify it.")

    val generatedFilePath = File(projectDir, "src/main/java/com/damilola")
    val packageName = "com.damilola.${featureName.lowercase()}"

    // Retrieve the base package name from the project configuration
//    val basePackageName = project.android.defaultConfig.applicationId
//        ?: error("Base package could not be detected. Ensure applicationId is set in defaultConfig.")

    // Construct the base output path for the generated files
    // val generatedFilePath = File(projectDir, "src/main/java/${packageName.replace(".", "/")}")

    // Map of placeholders to be replaced in template files
    val placeholders = mapOf(
        "{{packageName}}" to packageName,
        "{{featureName}}" to featureName.lowercase(),
        "{{className}}" to featureName.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    )

    doLast {
        val featureDir = File(generatedFilePath, featureName.lowercase())
        if (featureDir.exists()) error("Feature '$featureName' already exists at ${featureDir.path}.")
        featureDir.mkdirs()

        val templateDir = File(rootDir, "$rootFolderName/$templateName")
        check(templateDir.exists() && templateDir.isDirectory) {
            "Template directory '$templateDir' not found or is not a directory."
        }

        // Copy and customize template files
        templateDir.walkTopDown()
            .filter { it.isFile }
            .forEach { file ->
                val relativePath = file.relativeTo(templateDir).parent ?: ""
                val featureFileName = "${featureName}${file.name}"
                val targetFile = File(featureDir, "$relativePath/$featureFileName")
                targetFile.parentFile.mkdirs()

                // Read file content and replace placeholders
                var content = file.readText()
                placeholders.forEach { (key, value) ->
                    content = content.replace(key, value)
                }
                targetFile.writeText(content)
                println("Generated file: ${targetFile.path}")

            }

        println("Feature '$featureName' successfully generated at ${featureDir.path}.")
    }
}