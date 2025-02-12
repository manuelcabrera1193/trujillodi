plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.willard.cabrera.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures.buildConfig = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {

    // Ktor Client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)


    // Logging for Ktor (Optional for Debugging)
    implementation(libs.ktor.client.logging)

    // Retrofit Client
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Kotlinx Serialization (Optional for JSON handling)
    implementation(libs.kotlinx.serialization.json)

    // Coroutines for Asynchronous Operations
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // OkHttp for making HTTP requests
    api(libs.okhttp)
    api(libs.logging.interceptor)

    api(project(":domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}