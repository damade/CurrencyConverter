interface BuildType {

    companion object {
        const val DEBUG: String = "debug"
        const val RELEASE: String = "release"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
    val applicationIdSuffix: String
    val versionNameSuffix: String
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true
    override val applicationIdSuffix: String = ".debug"
    override val versionNameSuffix: String = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true
    override val applicationIdSuffix: String = ".release"
    override val versionNameSuffix: String = "-RELEASE"
}