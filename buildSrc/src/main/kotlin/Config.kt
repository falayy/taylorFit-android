object Config {

    object SdkVersions {
        const val compile = 28
        const val target = 28
        const val min = 21
    }

    object PublishVersion {
        const val name = "1.0.0"
        const val code = 1
    }

    object Libs {

        object Kotlin {
            internal const val kotlinVersion = "1.3.60"
            private const val coroutinesVersion = "1.2.1"
            const val kotlinJvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
            const val coroutinesCore =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            const val coroutinesAndroid =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        }

        object Test {
            const val jUnit = "junit:junit:4.12"
            const val mockito = "org.mockito:mockito-core:2.25.0"
            const val androidXTestRunner = "androidx.test:runner:1.2.0"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
            const val truth = "com.google.truth:truth:1.0.1"
        }

        object AndroidX {
            internal const val navigationVersion = "1.0.0"
            private const val lifeCycleVersion = "2.1.0-alpha01"
            const val lifeCycleExt = "androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion"
            const val lifeCycleReactive = "android.arch.lifecycle:reactivestreams:$lifeCycleVersion"
            const val lifeCycleViewModel =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion"
            const val navigationFragment =
                "android.arch.navigation:navigation-fragment-ktx:$navigationVersion"
            const val navigationUI = "android.arch.navigation:navigation-ui-ktx:$navigationVersion"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
            const val core = "androidx.core:core-ktx:1.0.1"
            const val recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
            const val browser = "androidx.browser:browser:1.0.0"
            const val legacySupportLib = "androidx.legacy:legacy-support-v4:1.0.0"
            const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"
            const val room = "androidx.room:room-runtime:2.0.0"
            const val roomCompiler = "androidx.room:room-compiler:2.0.0"
            const val workManager = "android.arch.work:work-runtime-ktx:1.0.0-alpha11"

        }

        object DI {
            private const val daggerVersion = "2.21"
            const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
            const val dagger = "com.google.dagger:dagger:$daggerVersion"
            const val glassFishAnnotation = "org.glassfish:javax.annotation:10.0-b28"
        }

        object Network {
            private const val retrofitVersion = "2.6.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
            const val retrofitGsonConverter =
                "com.squareup.retrofit2:converter-gson:$retrofitVersion"
            const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:$retrofitVersion"
            const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.0.1"
        }

        object Reactive {
            const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.17"
            const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
            const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
            const val rxRetrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:2.7.1"
        }

        object Misc {
            const val materialDesign = "com.google.android.material:material:1.1.0-alpha08"
            const val timber = "com.jakewharton.timber:timber:4.7.1"
            const val materialDialogs = "com.afollestad.material-dialogs:core:3.2.1"
            const val countryCodePicker = "com.github.joielechong:countrycodepicker:2.3.3"
            const val coil = "io.coil-kt:coil:0.9.1"
            const val googlePlayServices = "com.google.android.gms:play-services-location:17.0.0"
            const val firebaseStorage = "com.google.firebase:firebase-storage-ktx:19.1.1"
            const val pallete =  "com.android.support:palette-v7:28.0.0"

        }
    }

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:3.5.0"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:3.27.1"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.Kotlin.kotlinVersion}"
        const val navSaveArgs =
            "android.arch.navigation:navigation-safe-args-gradle-plugin:${Libs.AndroidX.navigationVersion}"
            const val googleServices = "com.google.gms:google-services:4.3.3"
    }

}
