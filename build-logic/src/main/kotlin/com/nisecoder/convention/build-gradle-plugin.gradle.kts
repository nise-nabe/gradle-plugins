package com.nisecoder.convention

import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

plugins {
    java
    `maven-publish`
    id("com.gradle.plugin-publish")
}

pluginBundle {
    website = "https://github.com/nise-nabe/gradle-plugins"
    vcsUrl = "https://github.com/nise-nabe/gradle-plugins"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation(gradleTestKit())
    testImplementation(kotlin("test-junit5"))
    testImplementation(project(":lib-gradle-test"))
    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/nise-nabe/gradle-plugins")
            // set ~/.gradle/gradle.properties
            // GitHubUsername
            // GitHubPassword
            credentials(PasswordCredentials::class)
        }
    }
}
