@file:Suppress("UnstableApiUsage")

rootProject.name = "gradle-plugins"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version embeddedKotlinVersion
        id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.8"
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

includeBuild("./build-logic")

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
