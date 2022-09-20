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

include(":core:navigation")
include(":core:design")
include(":core:network")

include(":ui:characters")
include(":ui:locations")
include(":ui:episodes")
