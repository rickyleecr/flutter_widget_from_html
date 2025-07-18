plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "dev.fwfh.demo_app"
    compileSdk = flutter.compileSdkVersion

    // TODO: remove hard-coded value when `flutter.ndkVersion` is sufficient
    // open $(dirname $(dirname $(which flutter)))/packages/flutter_tools
    ndkVersion = "27.0.12077973"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        applicationId = "dev.fwfh.demo_app"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName

        // https://patrol.leancode.co/getting-started
        testInstrumentationRunner = "pl.leancode.patrol.PatrolJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    signingConfigs {
        create("demo") {
            storeFile = file("../keystore")
            storePassword = "storepass"
            keyAlias = "alias"
            keyPassword = "keypass"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("demo")
        }
        release {
            signingConfig = signingConfigs.getByName("demo")
        }
    }

    testOptions {
        // https://patrol.leancode.co/getting-started
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

flutter {
    source = "../.."
}

dependencies {
    // https://patrol.leancode.co/getting-started
    androidTestUtil("androidx.test:orchestrator:1.5.1")
}
