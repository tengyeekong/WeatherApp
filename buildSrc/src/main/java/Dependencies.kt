object Testing {
    const val jUnit = "junit:junit:4.13.2"
    const val androidJUnit = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
}

object Dependencies {
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10"
    const val maps = "com.google.android.gms:play-services-maps:18.1.0"
}

object Hilt {
    private const val hiltVer = "2.47"

    const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVer"
    const val hilt = "com.google.dagger:hilt-android:$hiltVer"
    const val compiler = "com.google.dagger:hilt-compiler:$hiltVer"
}

object Gson {
    const val gson = "com.google.code.gson:gson:2.10.1"
}

object Android {
    private const val navigationVer = "2.7.1"

    const val gradle = "com.android.tools.build:gradle:7.4.2"
    const val material = "com.google.android.material:material:1.9.0"
    const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val navigationFragment = "androidx.navigation:navigation-fragment:$navigationVer"
    const val navigationUi = "androidx.navigation:navigation-ui:$navigationVer"
}
