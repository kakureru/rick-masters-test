pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "rick-masters-test"
include(":app")
include(":feature:cameras")
include(":feature:doors")
include(":component:cameras")
include(":component:doors")
include(":common:ui")
include(":common:utils")
include(":network")
include(":common:core")
