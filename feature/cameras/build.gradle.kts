import plugin.ComposeGradlePlugin
import plugin.ModuleGradlePlugin

plugins {
    `android-library`
    `kotlin-android`
}

apply<ModuleGradlePlugin>()
apply<ComposeGradlePlugin>()

android {
    namespace = "com.rickmasters.cameras"
}

dependencies {
    implementation(project(":common:ui"))

    implementation(Dependencies.coreKtx)
    compose()
    koin()
    implementation(Dependencies.coil)
}