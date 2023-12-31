import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val coreKtx = "androidx.core:core-ktx:1.12.0"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:2.6.2"
    const val activityCompose = "androidx.activity:activity-compose:1.8.0"
    const val navigationCompose = "androidx.navigation:navigation-compose:2.7.5"

    const val coil = "io.coil-kt:coil-compose:2.5.0"

    const val realm = "io.realm.kotlin:library-base:1.7.1"

    object Compose {
        const val bom = "androidx.compose:compose-bom:2023.10.01"
        const val runtime = "androidx.compose.runtime:runtime"
        const val foundation = "androidx.compose.foundation:foundation:1.6.0-alpha04"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout"
        const val material3 = "androidx.compose.material3:material3:1.2.0-alpha08"
        const val material = "androidx.compose.material:material:1.5.4"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
    }

    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1"

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    }
}

fun DependencyHandler.ktor() {
    implementation(Dependencies.Ktor.core)
    implementation(Dependencies.Ktor.android)
    implementation(Dependencies.Ktor.serialization)
    implementation(Dependencies.Ktor.logging)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.compose)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.lifecycleRuntimeCompose)

    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.foundationLayout)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.uiToolingPreview)
    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.uiTestManifest)
}