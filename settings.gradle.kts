pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "rm"

include(":app")
include(":ui:characters")
include(":core:navigation")
include(":ui:locations")
include(":ui:episodes")
include(":core:design")
