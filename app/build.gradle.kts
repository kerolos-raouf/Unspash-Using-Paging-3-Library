import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")

}

android {
    namespace = "com.example.testpaging3library"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testpaging3library"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperty = Properties()
        val file = rootProject.file("local.properties")
        if(file.exists())
        {
            file.inputStream().use {
                localProperty.load(it)
            }
        }

        val apiKey : String = localProperty.getProperty("API_KEY") ?: "null"


        buildConfigField("String","API_KEY",apiKey)


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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    /////dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    ///navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)




//retrofit and gson converter
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    //kotlinx serialization
    implementation(libs.kotlinx.serialization.json)

    ///room database
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    ///glide dependency for images
    implementation(libs.glide)


    ///paging library for kotlin
    implementation(libs.androidx.paging.common.ktx)
    implementation(libs.androidx.paging.runtime.ktx)


//Lifecycle for android Coroutines
    implementation(libs.androidx.lifecycle.runtime.ktx)


////more features in the activity like lazy initialization
    implementation(libs.androidx.activity.ktx)
////to use viewModel with fragment
    implementation(libs.androidx.fragment.ktx)
}


kapt {
    correctErrorTypes = true
}
