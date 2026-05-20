
plugins {
    kotlin("kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.googleDaggerHilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.billion_dollor_company.easypay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.billion_dollor_company.easypay"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

//    implementation(project(":cl:ui"))
    implementation(project(":npciCore:featureApi"))
    implementation(project(":npciCore:featureImpl"))
    implementation(project(":npciCore:common"))

    implementation(project(":core:common"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.runtime.compose)

    //datastore
    implementation(libs.androidx.datastore.preferences)

    //constraint layout
    implementation(libs.androidx.constraintlayout.compose)

    //viewmodel
    implementation(libs.lifecycle.viewmodel.compose)

    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    //navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.hilt.navigation.compose)


    //splash screen
    implementation(libs.androidx.core.splashscreen)

    // icons
    implementation(libs.androidx.material.icons.extended)

    //to read bar codes
    implementation(libs.play.services.code.scanner)

    //api calls
    implementation(libs.retrofit)
    implementation (libs.converter.gson)

    // lottie animation
    implementation (libs.lottie.compose)

    // to generate qr codes
    implementation (libs.compose.qr.code)
}