package gradle_helpers

import com.android.build.api.dsl.CommonExtension
import implementAll
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.implementationViewsDependencies(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            implementAll(list = listOf(

            ))

        }
    }
}