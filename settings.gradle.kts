@file:Suppress("UnstableApiUsage")

rootProject.name = "gradle-plugins"

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("org.jetbrains.gradle.plugin.idea-ext") version "1.0.1"
        id("com.gradle.plugin-publish") version "0.16.0"
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
