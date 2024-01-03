plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
}

gradlePlugin {
    plugins {
        create("github-release-upload") {
            id = "com.nisecoder.github-release-upload"
            implementationClass = "com.nisecoder.gradle.plugin.GitHubReleaseUploadPlugin"
            description = "convention plugin to upload to GitHub Release"
            tags = listOf("github", "github release")
        }
    }
}
