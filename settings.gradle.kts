rootProject.name = "gradle-plugins"

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "1.5.31"
        id("org.jetbrains.gradle.plugin.idea-ext") version "1.0.1"

    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include("idea-ext-ext")

for (project in rootProject.children) {
    val projectPath = file("subprojects/${project.name}")

    project.projectDir = projectPath
}
