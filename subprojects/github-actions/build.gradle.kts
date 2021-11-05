plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

gradlePlugin {
    plugins {
        register("github-actions") {
            id = "com.nisecoder.github-actions"
            implementationClass = "com.nisecoder.gradle.plugin.GitHubActionsPlugin"
            description = "convention plugin to run on GitHub Actions"
        }
    }
}

pluginBundle {
    (plugins) {
        "github-actions" {
            tags = listOf("github", "github actions", "ci")
        }
    }
}
