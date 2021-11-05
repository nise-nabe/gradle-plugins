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

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation(gradleTestKit())
    testImplementation(kotlin("test-junit5"))
    testImplementation(project(":lib-gradle-test"))
    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.test {
    useJUnitPlatform()
}
