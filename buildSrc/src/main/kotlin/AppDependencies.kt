object Config {
    object Version {
        const val minSdkVersion: Int = 24
        const val compileSdkVersion: Int = 35
        const val targetSdkVersion: Int = 35
        const val versionName: String = "1.0"
        const val versionCode: Int = 1
    }

    const val isMultiDexEnabled: Boolean = true

    const val isViewBindingEnabled = true

    object Android {
        const val applicationId: String = "com.damilola.currencyconverter"
        const val testInstrumentationRunner: String =
            "androidx.test.runner.AndroidJUnitRunner"
    }
}
