plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tengyeekong.weatherapp"
    compileSdk = Versions.TARGET_SDK

    defaultConfig {
        applicationId = "com.tengyeekong.weatherapp"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":app-data"))
    implementation(project(":app-domain"))
    implementation(project(":app-ui"))

    // Hilt
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)

    // Gson
    implementation(Gson.gson)

    implementation(Android.material)
}