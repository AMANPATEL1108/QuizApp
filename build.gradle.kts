// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // This allows applying the Android application plugin to subprojects
    alias(libs.plugins.android.application) apply false
}

// Note: In modern Gradle, allprojects is deprecated
// Repositories are now typically managed in settings.gradle.kts
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}