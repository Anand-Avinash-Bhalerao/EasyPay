pluginManagement {
    repositories {
        google()
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

rootProject.name = "EasyPay"
include(":app")
include(":cl:data")
include(":cl:domain")
include(":cl:ui")
include(":core:network")
include(":core:featureApi")
include(":core:common")
include(":npciCore:common")
include(":npciCore:featureApi")
