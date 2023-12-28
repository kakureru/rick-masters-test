plugins {
    kotlin("plugin.serialization") version "1.9.20"
    id("io.realm.kotlin") version "1.7.1" apply false
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}