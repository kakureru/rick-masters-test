import plugin.ComposeGradlePlugin
import plugin.ModuleGradlePlugin

plugins {
    `android-library`
    `kotlin-android`
}

apply<ModuleGradlePlugin>()
apply<ComposeGradlePlugin>()

android {
    namespace = "com.rickmasters.doors"
}

dependencies {
    implementation(project(":common:ui"))

    implementation(Dependencies.coreKtx)
    koin()
    compose()
    implementation(Dependencies.coil)
}