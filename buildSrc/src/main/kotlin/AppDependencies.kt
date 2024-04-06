object Config {
    object Version {
        const val minSdkVersion: Int = 24
        const val compileSdkVersion: Int = 34
        const val targetSdkVersion: Int = 34
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

object Jvm {
    const val kotlinCompileJvmVersion = "17"
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx: String = "1.12.0"
            const val navigation: String = "2.7.7"
            const val multidex: String = "2.0.1"
            const val lifeCycle: String = "2.7.0"
            const val activity: String = "1.8.2"
            const val paging: String = "3.2.1"
            const val legacy: String = "1.0.0"
        }

        const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val navigationDynamicFeatureKtx: String =
            "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigation}"
        const val multiDex: String = "androidx.multidex:multidex:${Version.multidex}"
        const val activity: String = "androidx.activity:activity:${Version.activity}"
        const val activityKtx: String = "androidx.activity:activity-ktx:${Version.activity}"
        const val lifeCycleCommon: String =
            "androidx.lifecycle:lifecycle-common-java8:${Version.lifeCycle}"
        const val viewModel: String =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
        const val liveData: String =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifeCycle}"
        const val savedState: String =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.lifeCycle}"
        const val runTime: String =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifeCycle}"
        const val paging: String =
            "androidx.paging:paging-runtime-ktx:${Version.paging}"
        const val legacy: String =
            "androidx.legacy:legacy-support-v4:${Version.legacy}"

        override val components: List<String>
            get() = listOf(
                coreKtx, navigationFragmentKtx, navigationUiKtx,
                navigationDynamicFeatureKtx, multiDex, activity,
                activityKtx, lifeCycleCommon, viewModel, liveData,
                runTime, savedState, legacy
            )
    }

    object View : Libraries {
        object Version {
            const val materialComponent: String = "1.5.0"
            const val shimmerLayout: String = "0.5.0"
            const val appCompat: String = "1.6.1"
            const val constraintLayout: String = "2.1.4"
            const val fragment: String = "1.6.2"
            const val cardView: String = "1.0.0"
            const val recyclerView: String = "1.3.2"
            const val swipeRefresh: String = "1.1.0"
            const val loadingButton: String = "2.2.0"
            const val countryPicker: String = "2.4.2"
            const val lottieAnimation: String = "4.2.1"
            const val pinView: String = "2.1.2"
            const val viewPagerIndicator: String = "4.1.2"
            const val cardNumberView: String = "2.1.3"
        }

        const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"
        const val swipeRefresh: String =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefresh}"
        const val cardView: String = "androidx.cardview:cardview:${Version.cardView}"
        const val materialComponent: String =
            "com.google.android.material:material:${Version.materialComponent}"
        const val shimmerLayout: String =
            "com.facebook.shimmer:shimmer:${Version.shimmerLayout}"
        const val constraintLayout: String =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val recyclerView: String =
            "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        const val loadingButton: String =
            "br.com.simplepass:loading-button-android:${Version.loadingButton}"
        const val countryPicker: String =
            "com.hbb20:ccp:${Version.countryPicker}"

        const val lottieAnimation: String =
            "com.airbnb.android:lottie:${Version.lottieAnimation}"
        const val pinView: String =
            "com.github.mukeshsolanki:android-otpview-pinview:${Version.pinView}"
        const val viewPagerIndicator: String =
            "com.tbuonomo.andrui:viewpagerdotsindicator:${Version.viewPagerIndicator}"
        const val cardNumberView: String =
            "com.github.Mostafa-MA-Saleh:EditCredit:${Version.cardNumberView}"

        override val components: List<String> = listOf(
            cardView, appCompat, fragment, swipeRefresh, lottieAnimation, pinView, cardNumberView,
            materialComponent, shimmerLayout, recyclerView
        )
    }

    object Compose : Libraries {
        object Version {
            const val composeActivity: String = "1.6.0"

            const val compose: String = "1.4.3"

            const val composeMaterial: String = "1.1.1"

            const val composeConstraint: String = "1.0.1"

            const val composeCompiler: String = "1.4.8"

            const val composeTheme: String = "1.1.3"

            const val composeViewModel: String = "2.5.1"

            const val composeNavigation: String = "2.6.0"

            const val composeHiltNavigation: String = "1.0.0"

            const val composeMDCTheme: String = "1.1.16"

            const val composeAppCompatTheme: String = "0.25.1"

        }

        const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
        const val composeConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Version.composeConstraint}"

        const val composeAnimation = "androidx.compose.animation:animation:${Version.compose}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Version.compose}"
        const val composeMaterial = "androidx.compose.material3:material3:${Version.composeMaterial}"
        const val composeMaterialWindow =
            "androidx.compose.material3:material3-window-size-class:${Version.composeMaterial}"

        const val composeUi = "androidx.compose.ui:ui:${Version.compose}"
        const val composeUiPreview = "androidx.compose.ui:ui-tooling:${Version.compose}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Version.compose}"
        const val composeRxJava = "androidx.compose.runtime:runtime-rxjava3:${Version.compose}"

        const val composeNavigation =
            "androidx.navigation:navigation-compose:${Version.composeNavigation}"
        const val composeViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.composeViewModel}"

        // When using a MDC theme
        const val composeMDCTheme =
            "com.google.android.material:compose-theme-adapter:${Version.composeMDCTheme}"

        const val composeHiltNavigation =
            "androidx.hilt:hilt-navigation-compose:${Version.composeHiltNavigation}"

        // When using a AppCompat theme
        const val composeAppCompatTheme =
            "com.google.accompanist:accompanist-appcompat-theme:${Version.composeAppCompatTheme}"

        // When using a Animation Graphics Dependency
        const val composeAnimationGraphics =
            "androidx.compose.animation:animation-graphics:${Version.compose}"

        // When using a Animation Graphics Core Dependency
        const val composeAnimationCore =
            "androidx.compose.animation:animation-core:${Version.compose}"

        const val composeRuntime =
            "androidx.compose.runtime:runtime:${Version.compose}"

        override val components: List<String> = listOf(
            composeActivity,
            composeAnimation,
            composeConstraintLayout,
            composeFoundation,
            composeMaterial,
            composeMaterialWindow,
            composeAppCompatTheme,
            composeMDCTheme,
            composeRxJava,
            composeUi,
            composeViewModel,
            composeNavigation,
            composeHiltNavigation,
            composeAnimationGraphics,
            composeRuntime,
        )
    }

    object FlowBinding {
        private const val flowBindingVersion: String = "1.0.0-alpha02"
        const val android: String =
            "io.github.reactivecircus.flowbinding:flowbinding-android:$flowBindingVersion"
        const val lifecycle: String =
            "io.github.reactivecircus.flowbinding:flowbinding-lifecycle:$flowBindingVersion"
    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "5.0.0-alpha.2"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.14.0"
        }

        object AnnotationProcessor {
            const val moshi: String = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshiKotlin: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        const val moshi: String = "com.squareup.moshi:moshi:${Version.moshi}"

        override val components: List<String> = listOf(
            okhttp,
            loggingInterceptor,
            retrofit,
            retrofitMoshi,
            moshiKotlin
        )
    }

    object DI {
        object Version {
            const val daggerHilt: String = "2.44"
            const val androidxHilt: String = "1.0.0"
        }

        object AnnotationProcessor {
            const val daggerHilt: String =
                "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
            const val androidxHiltCompiler: String =
                "androidx.hilt:hilt-compiler:${Version.androidxHilt}"
        }

        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"

        const val hiltViewModel: String =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Version.androidxHilt}"
        const val hiltTesting: String =
            "com.google.dagger:hilt-android-testing:${Version.daggerHilt}"
        const val hiltCore: String = "com.google.dagger:hilt-core:${Version.daggerHilt}"
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.7.3"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val android: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object RxJava : Libraries {
        object Version {
            const val rxjava3RxandroidVersion: String = "3.0.0"

            const val rxjava3Version: String = "3.1.5"

            const val rxrelay3Version: String = "3.0.1"
        }

        const val rxJavaCore = "io.reactivex.rxjava3:rxjava:${Version.rxjava3Version}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Version.rxjava3RxandroidVersion}"

        const val rxRelay = "com.jakewharton.rxrelay3:rxrelay:${Version.rxrelay3Version}"

        override val components: List<String> = listOf(rxJavaCore, rxAndroid, rxRelay)
    }

    object ApolloGraphQl : Libraries {
        object Version {
            const val apollographqlVersion: String = "3.6.2"
        }

        // region Apollo
        const val apolloRuntime =
            "com.apollographql.apollo3:apollo-runtime:${Version.apollographqlVersion}"
        const val apolloApi =
            "com.apollographql.apollo3:apollo-api:${Version.apollographqlVersion}"
        const val apolloRxJava3 =
            "com.apollographql.apollo3:apollo-rx3-support:${Version.apollographqlVersion}"

        override val components: List<String> = listOf(apolloRxJava3, apolloRuntime)
    }

    object Image {
        object Version {
            const val imageSlider: String = "0.1.0"
            const val circleImageView: String = "3.1.0"
            const val glideLoader: String = "4.12.0"
            const val picassoLoader: String = "3.1.0"
            const val coilLoader: String = "2.2.1"
        }

        const val circleImageView: String =
            "de.hdodenhof:circleimageview:${Version.circleImageView}"

        const val imageSlider: String =
            "com.github.denzcoskun:ImageSlideshow:${Version.imageSlider}"

        const val glideLoader: String =
            "com.github.bumptech.glide:glide:${Version.glideLoader}"

        const val glideCompiler: String =
            "com.github.bumptech.glide:compiler:${Version.glideLoader}"

        const val coilLoader: String =
            "io.coil-kt:coil:${Version.coilLoader}"

        const val coilComposeLoader: String =
            "io.coil-kt:coil-compose:${Version.coilLoader}"
    }

    object Cache : Libraries {
        object Version {
            const val room: String = "2.5.2"
        }

        object AnnotationProcessor {
            const val room: String = "androidx.room:room-compiler:${Version.room}"
        }

        const val room: String = "androidx.room:room-ktx:${Version.room}"
        private const val roomRuntime: String = "androidx.room:room-runtime:${Version.room}"
        private const val roomRxJava: String = "androidx.room:room-rxjava3:${Version.room}"

        override val components: List<String> = listOf(room, roomRuntime, roomRxJava)
    }

    object Performance {
        object Version {
            const val leakCanary: String = "2.7"
            const val logger: String = "0.1"
        }

        const val leakCanary: String =
            "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"
        const val logger: String =
            "com.squareup.logcat:logcat:${Version.logger}"
    }

    object Test {
        object Version {
            const val junit: String = "4.13.2"
            const val runner: String = "1.2.0"
            const val rules: String = "1.3.0-rc03"
            const val testExt: String = "1.1.3"
            const val espresso: String = "3.4.0"
            const val truth: String = "1.0.1"
            const val mockWebServer: String = "4.7.2"
            const val robolectric: String = "4.5.1"
            const val archCoreTest: String = "1.1.1"
            const val fragment: String = "1.4.0"
            const val compose: String = "1.2.1"
            const val coroutines: String = "1.6.4"
            const val turbine: String = "0.12.1"
        }

        const val junit: String = "junit:junit:${Version.junit}"
        const val runner: String = "androidx.test:runner:${Version.runner}"
        const val fragmentTesting: String = "androidx.fragment:fragment-testing:${Version.fragment}"
        const val androidXTest: String = "androidx.test.ext:junit:${Version.testExt}"
        const val espresso: String = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val espressoContrib: String =
            "androidx.test.espresso:espresso-contrib:${Version.espresso}"
        const val rules: String = "androidx.test:rules:${Version.rules}"
        const val archCoreTest: String = "android.arch.core:core-testing:${Version.archCoreTest}"
        const val truth: String = "com.google.truth:truth:${Version.truth}"
        const val mockWebServer: String =
            "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
        const val coroutinesTest: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"

        const val robolectric: String = "org.robolectric:robolectric:${Version.robolectric}"

        const val compose: String = "androidx.compose.ui:ui-test-junit4:${Version.compose}"

        const val turbine: String = "app.cash.turbine:turbine:${Version.turbine}"
    }
}

object ProjectLib {
    const val app: String = ":app"
    const val core: String = ":core"
    const val coreAndroid: String = ":core-android"
    const val libComposeCore: String = ":lib_compose_core"
    const val remote: String = ":libraries:remote"
    const val cache: String = ":libraries:cache"
    const val config: String = ":config"
    const val libCurrencySearch: String = ":lib_currency_search"
    const val libCurrency: String = ":lib_currency"
    const val testUtils: String = ":libraries:testUtils"
    const val ftCurrency: String = ":ft_currency"
    const val ftHome: String = ":ft_home"
    const val navigation: String = ":navigation"
}
