plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")
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
    website = "https://github.com/nise-nabe/gradle-plugins"
    vcsUrl = "https://github.com/nise-nabe/gradle-plugins"

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
