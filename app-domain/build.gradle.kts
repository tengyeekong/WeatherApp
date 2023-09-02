plugins {
    id("com.android.library")
}

android {
    namespace = "com.tengyeekong.weatherapp.domain"
    compileSdk = Versions.TARGET_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
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
    // Hilt
    implementation(Hilt.hilt)

    // Gson
    implementation(Gson.gson)
}