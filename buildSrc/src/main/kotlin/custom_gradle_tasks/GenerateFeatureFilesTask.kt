package custom_gradle_tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.Locale

open class GenerateFeatureFilesTask : DefaultTask() {
    @TaskAction
    fun generateFeatureFiles() {
        val rootFolderName = "projectTemplate"

        val featureName = project.findProperty("featureName") as String?
            ?: error("Feature name is required. Use -PfeatureName=<name> to specify it.")

        val templateName = project.findProperty("templateName") as String?
            ?: error("Template name is required. Use -PtemplateName=<name> to specify it.")

        val generatedFilePath = File(project.projectDir, "src/main/java/com/damilola")
        val packageName = "com.damilola.${featureName.lowercase()}"

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
}