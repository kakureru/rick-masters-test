import plugin.ModuleGradlePlugin

plugins {
    `android-library`
    `kotlin-android`
}

apply<ModuleGradlePlugin>()

android {
    namespace = "com.rickmasters.component.doors"
}

dependencies {
    implementation(Dependencies.Koin.android)
}