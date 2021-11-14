plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

gradlePlugin {
    plugins {
        register("github-publish") {
            id = "com.nisecoder.github-publish"
            implementationClass = "com.nisecoder.gradle.plugin.GitHubPublishPlugin"
            description = "convention plugin for publication to GitHub"
        }
    }
}

pluginBundle {
    (plugins) {
        "github-publish" {
            tags = listOf("github")
        }
    }
}
