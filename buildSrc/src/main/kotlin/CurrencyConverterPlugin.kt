import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.repositories

class CurrencyConverterPlugin : BaseGradlePlugin() {

    override fun apply(target: Project) {
        with(target) {
            plugins.all {
                when (this) {
                    is AppPlugin ->
                        extensions.getByType<TestedExtension>().apply {
                            setUpAndroidAppModules(extension = this)
                        }

                    is LibraryPlugin,
                        -> extensions.getByType<TestedExtension>().apply {
                        setUpAndroidLibraryModules(extension = this)
                    }

                    is JavaPlugin, is JavaLibraryPlugin ->
                        extensions.getByType<JavaPluginExtension>().setupModule()
                }
            }
            setupRepositoriesHandler()
        }
    }

    private fun Project.setupRepositoriesHandler() = repositories {
        applyDefault()
    }

    private fun setUpAndroidLibraryModules(extension: TestedExtension) =
        extension.applyCommonProperties(isAppPlugin = false)

    private fun setUpAndroidAppModules(extension: TestedExtension) =
        extension.applyCommonProperties(isAppPlugin = true)

    private fun JavaPluginExtension.setupModule() = apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
}

private fun TestedExtension.applyCommonProperties(isAppPlugin: Boolean): TestedExtension {
    return this.apply {
        setCompileSdkVersion(Config.Version.compileSdkVersion)

        viewBinding.isEnabled = Config.isViewBindingEnabled

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
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