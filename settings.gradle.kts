rootProject.name = "gradle-plugins"

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

for (project in rootProject.children) {
    val projectPath = file("subprojects/${project.name}")

    project.projectDir = projectPath
}
