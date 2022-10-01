// TODO Remove suppression after fix bug https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "org.skyfaced.episodes"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "${projectDir}/proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))

    implementation(libs.composeUi)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeMaterial)
    implementation(libs.material3)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltAndroidCompiler)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.lifecycleRuntimeCompose)
    implementation(libs.lifecycleViewModelCompose)
    implementation(libs.coilCompose)
    implementation(libs.accompanistPlaceholderMaterial)
}