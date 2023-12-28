import plugin.ModuleGradlePlugin

plugins {
    `android-library`
    `kotlin-android`
    kotlin("plugin.serialization")
    id("io.realm.kotlin")
}

apply<ModuleGradlePlugin>()

android {
    namespace = "com.rickmasters.component.doors"
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:utils"))
    implementation(project(":network"))

    implementation(Dependencies.Koin.android)
    implementation(Dependencies.kotlinxSerialization)
    ktor()
    implementation(Dependencies.realm)
}