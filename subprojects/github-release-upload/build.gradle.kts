plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

dependencies {
    implementation(libs.jackson.module.kotlin)
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
