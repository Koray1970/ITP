plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("C:\\Users\\user\\Documents\\ITP Documents\\publishkeys")
            storePassword = "Qwe123.."
            keyPassword = "Qwe123.."
            keyAlias = "itp0"
        }
    }
    namespace = "com.isticpla.itp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.isticpla.itp"
        minSdk = 24
        targetSdk = 33
        versionCode = 6
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0-alpha10")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.0-alpha08")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("com.google.android.material:material:1.10.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    implementation("androidx.compose.foundation:foundation-layout:1.6.0-alpha08")
    implementation("androidx.compose.foundation:foundation-layout-android:1.6.0-alpha08")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-beta01")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0-alpha08")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    // When using Kotlin.
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    // When using Java.
    annotationProcessor("androidx.hilt:hilt-compiler:1.1.0")
    implementation("com.google.code.gson:gson:2.10.1")
    //region Camera dependency
    // CameraX core library using the camera2 implementation
    val camerax_version = "1.4.0-alpha02"
    // The following line is optional, as the core library is included indirectly by camera-camera2
    implementation("androidx.camera:camera-core:${camerax_version}")
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    // If you want to additionally use the CameraX Lifecycle library
    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
    // If you want to additionally use the CameraX VideoCapture library
    implementation("androidx.camera:camera-video:${camerax_version}")
    // If you want to additionally use the CameraX View class
    implementation("androidx.camera:camera-view:${camerax_version}")
    // If you want to additionally add CameraX ML Kit Vision Integration
    implementation("androidx.camera:camera-mlkit-vision:${camerax_version}")
    // If you want to additionally use the CameraX Extensions library
    implementation("androidx.camera:camera-extensions:${camerax_version}")
    //endregion
    //region room database dependecy
    val room_version = "2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    //ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    // optional - RxJava2 support for Room
    //implementation("androidx.room:room-rxjava2:$room_version")

    // optional - RxJava3 support for Room
    //implementation("androidx.room:room-rxjava3:$room_version")

    // optional - Guava support for Room, including Optional and ListenableFuture
    //implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")
    //endregion
}
kapt {
    correctErrorTypes = true
}