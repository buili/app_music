plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.music"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.music"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //thư viện ảnh Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    dependencies {
        implementation("me.relex:circleindicator:2.1.6")
    }
}