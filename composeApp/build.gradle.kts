import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
}

sqldelight {
    databases {
        create("PokemonsDatabase") {
            packageName.set("org.lumstep")
            generateAsync.set(true)
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            // For network loading
            implementation(libs.ktor.client.android)

            // For dependency injection
            implementation(libs.koin.android)

            // For database
            implementation(libs.sqldelight.android.driver)

            // For lottie
            implementation(libs.lottie)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            // For network loading
            implementation(libs.ktor.client.apache5)

            // For coroutines
            implementation(libs.kotlinx.coroutines.swing)

            // For database
            implementation(libs.sqldelight.sqlite.driver)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

            // For database
            implementation(libs.sqldelight.native.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            // For loading images
            implementation(libs.kamel.image)

            // For network loading
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.encoding)

            // For serialization
            implementation(libs.kotlinx.serialization)

            // For pagination
            implementation(libs.paging.compose)
            implementation(libs.paging.common)

            // For logging
            implementation(libs.logging)

            // For coroutines
            implementation(libs.kotlinx.coroutines.core)

            // For compose navigation
            implementation(libs.decompose)
            implementation(libs.decompose.jetbrains)

            // For dependency injection
            implementation(libs.koin.core)

            // For dynamic theme
            implementation(libs.materialKolor)
        }
    }
}

android {
    namespace = "org.lumstep.pokemons"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.lumstep.pokemons"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.lumstep.pokemons"
            packageVersion = "1.0.0"
        }
    }
}
