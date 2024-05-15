    plugins {
        id("com.android.application") version "8.1.1" apply false
        id("org.jetbrains.kotlin.android") version "1.9.0" apply false
        id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
        id("com.google.dagger.hilt.android") version "2.44.2" apply false
    }

    buildscript {
        repositories {
            google()
            mavenCentral()
        }
        dependencies {
            classpath ("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
            classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
            classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
            classpath ("com.android.tools.build:gradle:8.1.1")
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        }
    }

    tasks.register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }