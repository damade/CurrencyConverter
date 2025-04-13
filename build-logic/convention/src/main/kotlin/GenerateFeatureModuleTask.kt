import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.Locale

open class GenerateFeatureModuleTask : DefaultTask() {

    init {
        group = "Custom Tasks"
        description = "Creates a new Android Library module."
    }

    @TaskAction
    fun generateFeatureModule() {
        val featureName = project.properties["featureName"] as? String
            ?: error("Feature name is required. Use -PfeatureName=<name> to specify it.")

        val templateName = project.properties["templateName"] as? String
            ?: error("Template name is required. Use -PtemplateName=<name> to specify it.")

        val libraryName = "ft_${featureName.lowercase()}"

        val moduleDir = File("${project.projectDir}/$libraryName")

        if (moduleDir.exists()) {
            throw IllegalArgumentException("Feature $featureName in module $libraryName already exists.")
        }

        moduleDir.mkdirs()

        // Create build.gradle.kts
        createBuildGradleKts(moduleDir, libraryName)

        // Create source directories
        File(moduleDir, "src/main/java").mkdirs()
        File(moduleDir, "src/main/res").mkdirs()

        // Create AndroidManifest.xml
        createAndroidManifest(moduleDir)

        // Include the new module in settings.gradle.kts
        includeModuleInSettings(libraryName)

        generateFeatureFiles(
            moduleDir = moduleDir,
            libraryName = libraryName,
            templateName = templateName,
            featureName = featureName.replaceFirstChar { it.titlecase(Locale.getDefault()) }
        )

        println("Android Library '$libraryName' created successfully.")
    }

    private fun createBuildGradleKts(moduleDir: File, libraryName: String) {
        val buildGradleContent = """
            import Dependencies.Compose
            import Dependencies.DI
            import Dependencies.Test
            import Dependencies.View
            import ProjectLib.core
            import ProjectLib.coreAndroid
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
                namespace = "com.damilola.$libraryName" // Customize namespace
                
                composeOptions {
                    kotlinCompilerExtensionVersion = Compose.Version.composeCompiler
                }
                
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
                
                debugImplementation(Compose.composeUiPreview)
                debugImplementation(Compose.composeUiTestManifest)

                implementAll(Compose.components)
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
        """.trimIndent()

        File(moduleDir, "build.gradle.kts").writeText(buildGradleContent)
    }

    private fun createAndroidManifest(moduleDir: File) {
        val manifestContent = """
            <?xml version="1.0" encoding="utf-8"?>
            <manifest />
        """.trimIndent()
        File(moduleDir, "src/main/AndroidManifest.xml").writeText(manifestContent)
    }

    private fun includeModuleInSettings(libraryName: String) {
        val settingsFile = File(project.rootDir, "settings.gradle.kts")
        val includeLine = "include(\":$libraryName\")\n"
        if (!settingsFile.readText().contains(includeLine)) {
            settingsFile.appendText(includeLine)
        }
    }

    private fun generateFeatureFiles(
        moduleDir: File,
        libraryName: String,
        templateName: String,
        featureName: String,
    ) {
        val rootFolderName = "projectTemplate"
        val packageName = "com.damilola.$libraryName"

        // Map of placeholders to be replaced in template files
        val placeholders = mapOf(
            "{{packageName}}" to packageName,
            "{{className}}" to featureName,
        )

        val featureDir = File(moduleDir, "src/main/java/com/damilola/${libraryName}")
        if (featureDir.exists()) error("Feature '$featureName' already exists at ${featureDir.path}.")
        featureDir.mkdirs()

        val templateDir = File(project.rootDir, "$rootFolderName/$templateName")
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