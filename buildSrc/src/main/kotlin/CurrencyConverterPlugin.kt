import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CurrencyConverterPlugin : BaseGradlePlugin() {

    override fun apply(target: Project) {
        with(target) {
            plugins.all {
                when (this) {
                    is AppPlugin ->
                        extensions.getByType<TestedExtension>().apply {
                            setUpAndroidAppModules(extension = this, project = project)
                        }

                    is LibraryPlugin,
                    -> extensions.getByType<TestedExtension>().apply {
                        setUpAndroidLibraryModules(extension = this, project = project)
                    }
                }
            }
            setupRepositoriesHandler()
        }
    }

    private fun Project.setupRepositoriesHandler() = repositories {
        applyDefault()
    }

    private fun setUpAndroidLibraryModules(
        project: Project,
        extension: TestedExtension,
    ) {
        extension.applyCommonProperties(project = project, isAppPlugin = false)
    }

    private fun setUpAndroidAppModules(
        project: Project,
        extension: TestedExtension,
    ) {
        extension.applyCommonProperties(project = project, isAppPlugin = true)
    }
}

private fun TestedExtension.applyCommonProperties(
    project: Project,
    isAppPlugin: Boolean,
): TestedExtension {
    return this.apply {
        setCompileSdkVersion(Config.Version.compileSdkVersion)

        viewBinding.isEnabled = Config.isViewBindingEnabled

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        project.tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = Jvm.kotlinCompileJvmVersion
        }

        if (isAppPlugin) {
            defaultConfig.apply {
                applicationId = Config.Android.applicationId
                minSdk = Config.Version.minSdkVersion
                targetSdk = Config.Version.targetSdkVersion
                versionCode = Config.Version.versionCode
                versionName = Config.Version.versionName
                multiDexEnabled = Config.isMultiDexEnabled
                testInstrumentationRunner = Config.Android.testInstrumentationRunner
                vectorDrawables {
                    useSupportLibrary = true
                }
                consumerProguardFiles("consumer-rules.pro")
            }
            buildTypes.apply {
                getByName(BuildType.DEBUG).run {
                    isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
                    applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
                    versionNameSuffix = BuildTypeDebug.versionNameSuffix
                }
                getByName(BuildType.RELEASE).run {
                    isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
                    applicationIdSuffix = BuildTypeRelease.applicationIdSuffix
                    versionNameSuffix = BuildTypeRelease.versionNameSuffix
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
            }
        } else {
            defaultConfig.apply {
                minSdk = Config.Version.minSdkVersion
                targetSdk = Config.Version.targetSdkVersion
                multiDexEnabled = Config.isMultiDexEnabled
                testInstrumentationRunner = Config.Android.testInstrumentationRunner
                vectorDrawables {
                    useSupportLibrary = true
                }
                consumerProguardFiles("consumer-rules.pro")
            }
            buildTypes.apply {
                getByName(BuildType.DEBUG).run {
                    isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
                }
                getByName(BuildType.RELEASE).run {
                    isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
                }
            }
        }
        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

inline fun <reified T : Any> ExtensionContainer.getByType(): T = getByType(T::class.java)