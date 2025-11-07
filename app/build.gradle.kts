plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.smartnotes"
    compileSdk = 36


    defaultConfig {
        applicationId = "com.example.smartnotes"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

// In app/build.gradle.kts

    dependencies {
        // Standard AndroidX libraries
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)

        // Room for local database
        implementation("androidx.room:room-runtime:2.6.1")
        kapt("androidx.room:room-compiler:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")

        // ViewModel and LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")

        // Google Sign-In
        implementation("com.google.android.gms:play-services-auth:21.2.0")

        // 1. Declare the Firebase Bill of Materials (BoM). This manages all Firebase versions.
        //    The version 32.1.0 from your context is fine, but using a more recent one is better.
        implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

        // 2. Add the Firebase Authentication library.
        //    DO NOT add "-ktx" and DO NOT specify a version number. The BoM handles it.
        implementation("com.google.firebase:firebase-auth")

        // Add other Firebase libraries you need here, for example:
        // implementation("com.google.firebase:firebase-firestore")

        // Testing libraries
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }
}
dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}


