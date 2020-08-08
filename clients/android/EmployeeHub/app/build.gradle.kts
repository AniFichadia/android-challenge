import Versions.desugarJdkVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(CommonAndroidConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(CommonAndroidConfig.minSdkVersion)
        targetSdkVersion(CommonAndroidConfig.targetSdkVersion)

        applicationId = "com.anifichadia.employeehub"
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("clearPackageData", "true")
    }

    compileOptions {
        coreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        generateStubs = true
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("${rootProject.projectDir}/employeehub_debug.jks")
            keyAlias = "employeehub"
            keyPassword = "employeehub"
            storePassword = "employeehub"
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false

            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            // TODO: setup a prod signing config
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        unitTests.isReturnDefaultValues = true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$desugarJdkVersion")

    Deps.kotlin(this)
    Deps.logging(this)
    Deps.dependencyInjection(this)
    Deps.androidArchitecture(this)
    Deps.androidUi(this)
    Deps.networkingStack(this)

    Deps.unitTest(this)

    Deps.androidUiTest(this)
}
