plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tengyeekong.weatherapp.ui"
    compileSdk = Versions.TARGET_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
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
}

dependencies {
    implementation(project(":app-domain"))

    // Hilt
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)

    // Gson
    implementation(Gson.gson)

    implementation(Android.appcompat)
    implementation(Android.material)
    implementation(Android.constraintLayout)
    implementation(Android.navigationFragment)
    implementation(Android.navigationUi)

    implementation(Dependencies.maps)

    implementation(Testing.jUnit)
    implementation(Testing.androidJUnit)
    implementation(Testing.espresso)
}