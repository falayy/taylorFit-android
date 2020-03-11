import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.jetbrains.kotlin.konan.properties.Properties
import com.android.build.gradle.api.BaseVariantOutput

plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("kotlin-android")
}


android {
    compileSdkVersion(Config.SdkVersions.compile)

    defaultConfig {
        applicationId = "com.tailorfit.android.tailorfit"
        minSdkVersion(Config.SdkVersions.min)
        targetSdkVersion(Config.SdkVersions.target)
        versionCode = Config.PublishVersion.code
        versionName = Config.PublishVersion.name
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    androidExtensions {
        isExperimental = true
    }

    signingConfigs {
        val keystorePropertiesPath = "keystore.properties"
        if (rootProject.file(keystorePropertiesPath).exists()) {
            val keystoreProperties = Properties()
            keystoreProperties.load(File(keystorePropertiesPath).inputStream())
            create("config") {
                storeFile = rootProject.file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            versionNameSuffix = "-release"
            signingConfig = signingConfigs.getByName("config")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
            signingConfig = signingConfigs.getByName("config")
        }
    }

    flavorDimensions("implementation")

    productFlavors {
        create("staging") {
            buildConfigField("String", "API_BASE_URL", "\"https://taylorfit.herokuapp.com/\"")
            buildConfigField("String", "OAUTH_CLIENT_ID", "\"API\"")
            buildConfigField("String", "OAUTH_CLIENT_SECRET", "\"API\"")
            buildConfigField("String", "OAUTH_GRANT_TYPE", "\"API\"")
            setDimension("implementation")
            versionNameSuffix = "-staging"
        }
        create("production") {
            buildConfigField("String", "API_BASE_URL", "\"https://taylorfit.herokuapp.com/\"")
            buildConfigField("String", "OAUTH_CLIENT_ID", "\"API-creds\"")
            buildConfigField("String", "OAUTH_CLIENT_SECRET", "\"API-creds\"")
            buildConfigField("String", "OAUTH_GRANT_TYPE", "\"API-creds\"")
            setDimension("implementation")
            versionNameSuffix = "-production"
        }
    }

    applicationVariants.all(object : Action<ApplicationVariant> {
        override fun execute(variant: ApplicationVariant) {
            variant.outputs.map { it as BaseVariantOutputImpl }
                .forEach { output ->
                    println("The variant is: ${variant.versionName}")
                    output.outputFileName = "taylorFit-${variant.versionName}.apk"
                }
        }
    })
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    //Tests
    testImplementation(Config.Libs.Test.jUnit)
    testImplementation(Config.Libs.Test.mockito)
    androidTestImplementation(Config.Libs.Test.androidXTestRunner)
    androidTestImplementation(Config.Libs.Test.espressoCore)

    //Kotlin
    implementation(Config.Libs.Kotlin.kotlinJvm)
    implementation(Config.Libs.Kotlin.coroutinesCore)
    implementation(Config.Libs.Kotlin.coroutinesAndroid)

    // AndroidX
    implementation(Config.Libs.AndroidX.constraintLayout)
    implementation(Config.Libs.AndroidX.browser)
    implementation(Config.Libs.AndroidX.core)
    implementation(Config.Libs.AndroidX.lifeCycleViewModel)
    implementation(Config.Libs.AndroidX.lifeCycleExt)
    implementation(Config.Libs.AndroidX.navigationFragment)
    implementation(Config.Libs.AndroidX.navigationUI)
    implementation(Config.Libs.AndroidX.recyclerView)
    implementation(Config.Libs.AndroidX.legacySupportLib)
    implementation(Config.Libs.AndroidX.viewPager2)
    implementation(Config.Libs.AndroidX.lifeCycleReactive)
    implementation(Config.Libs.AndroidX.room)
    kapt(Config.Libs.AndroidX.roomCompiler)

    //work manager
    implementation(Config.Libs.AndroidX.workManager)

    //DI with Dagger
    kapt(Config.Libs.DI.daggerCompiler)
    implementation(Config.Libs.DI.dagger)
    compileOnly(Config.Libs.DI.glassFishAnnotation)

    //Network
    implementation(Config.Libs.Network.retrofit)
    implementation(Config.Libs.Network.retrofitGsonConverter)
    implementation(Config.Libs.Network.okhttpLoggingInterceptor)
    implementation(Config.Libs.Network.retrofitMock)

    //Misc
    implementation(Config.Libs.Misc.materialDesign)
    implementation(Config.Libs.Misc.materialDialogs)
    implementation(Config.Libs.Misc.timber)
    implementation(Config.Libs.Misc.countryCodePicker)
    implementation(Config.Libs.Misc.glide)
    implementation(Config.Libs.Misc.googlePlayServices)

    //Rx
    implementation(Config.Libs.Reactive.rxJava)
    implementation(Config.Libs.Reactive.rxAndroid)
    implementation(Config.Libs.Reactive.rxKotlin)
    implementation(Config.Libs.Reactive.rxRetrofitAdapter)
}

apply(from = "../spotless.gradle")
