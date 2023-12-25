package plugin

import compose
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.android().apply {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.composeCompiler
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }

        project.dependencies.apply {
            compose()
        }
    }
}