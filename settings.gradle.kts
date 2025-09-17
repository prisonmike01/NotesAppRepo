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

    /*
    // This enables the version catalog named "libs" from libs.versions.toml
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    } */
}

rootProject.name = "NotesApp"
include(":app")
