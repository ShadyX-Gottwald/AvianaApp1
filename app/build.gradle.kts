plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "skosana.avianaapp1"
    compileSdk = 35

    defaultConfig {
        applicationId = "skosana.avianaapp1"
        minSdk = 30
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //implementation("androidx.compose.material3:material3")
    //Google Maps Dependency
    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation("com.google.maps.android:maps-compose:6.1.0")

    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("androidx.compose.ui:ui-test-android:1.7.5")
    //WOrk Manager
    implementation("androidx.work:work-runtime-ktx:2.10.0")


    //ktor

    val ktorVersion = "1.5.0"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    //COil
    implementation("io.coil-kt:coil-compose:2.7.0")



    //Coroutines
    val coroutinesVersion = "1.6.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    // Coroutines - Deferred adapter
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //Google Apis
    implementation ("com.google.android.libraries.places:places:4.0.0")

    implementation("com.google.firebase:firebase-auth:23.1.0")
    implementation("com.google.firebase:firebase-firestore:25.1.1")
    implementation("com.google.firebase:firebase-storage:21.0.1")
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("androidx.compose.material3:material3-android:1.3.1")
    implementation ("androidx.compose.material:material-icons-extended:")
    implementation("androidx.datastore:datastore-preferences-core-jvm:1.1.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation ("androidx.datastore:datastore-preferences-rxjava2:1.1.1")
    implementation ("androidx.datastore:datastore-preferences-rxjava3:1.1.1")
    testImplementation("junit:junit:4.13.2")
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
   // androidTestImplementation("androidx.test.ext:junit:1.2.1")
   // androidTestImplementation(platform("androidx.compose:compose-bom:2024.10.01"))
 //   androidTestImplementation("'androidx.test.espresso:espresso-core: 3.5.0")
  //  testImplementation("androidx.compose.ui:ui-test-junit4:1.7.5")
   // androidTestImplementation("androidx.test.ext:junit:1.2.1")

  //  androidTestImplementation(platform("androidx.compose:compose-bom:2024.10.01"))
   // androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}