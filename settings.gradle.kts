@file:Suppress("UnstableApiUsage")

rootProject.name = "gradle-plugins"

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.3"
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

includeBuild("./build-logic")

include("idea-ext-ext")
include("ci-detect")
include("asciidoctor")
include("github-actions")
include("lib-gradle-test")
include("publish-intellij-plugin")
include("github-release-upload")

for (project in rootProject.children) {
    val projectPath = file("subprojects/${project.name}")

    project.projectDir = projectPath
}
