plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

gradlePlugin {
    plugins {
        register("github-release-upload") {
            id = "com.nisecoder.github-release-upload"
            implementationClass = "com.nisecoder.gradle.plugin.GitHubReleaseUpload"
            description = "convention plugin to upload to GitHub Release"
        }
    }
}

pluginBundle {
    (plugins) {
        "github-release-upload" {
            tags = listOf("github", "github release")
        }
    }
}
