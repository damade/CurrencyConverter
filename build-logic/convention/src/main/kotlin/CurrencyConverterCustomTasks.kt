import org.gradle.api.Plugin
import org.gradle.api.Project

class CurrencyConverterCustomTasks : Plugin<Project> {

    override fun apply(project: Project) {

        project.tasks.register(
            "generateFeatureModule",
            GenerateFeatureModuleTask::class.java,
        )
    }
}