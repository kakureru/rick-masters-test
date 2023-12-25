import plugin.ModuleGradlePlugin

plugins {
    `android-library`
    `kotlin-android`
}

apply<ModuleGradlePlugin>()

android {
    namespace = "com.rickmasters.component.cameras"
}

dependencies {
    implementation(Dependencies.Koin.android)
}