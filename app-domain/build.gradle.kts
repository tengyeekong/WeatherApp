plugins {
    id("com.android.library")
}

android {
    namespace = "com.tengyeekong.weatherapp.domain"
    compileSdk = Versions.TARGET_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK

        buildConfigField("String", "API_KEY", "\"c6e381d8c7ff98f0fee43775817cf6ad\"")
    }

    buildFeatures {
        buildConfig = true
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