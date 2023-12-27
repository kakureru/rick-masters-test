import plugin.ComposeGradlePlugin
import plugin.ModuleGradlePlugin

plugins {
    `android-library`
    `kotlin-android`
}

apply<ModuleGradlePlugin>()
apply<ComposeGradlePlugin>()

android {
    namespace = "com.rickmasters.myhome"
}

dependencies {
    implementation(project(":common:ui"))
    implementation(project(":feature:doors"))
    implementation(project(":feature:cameras"))

    compose()
}