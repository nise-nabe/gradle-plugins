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

testing {
    @Suppress("UNUSED_VARIABLE")
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.8.2")

            dependencies {
                implementation(project.dependencies.gradleTestKit())
                implementation(project.dependencies.kotlin("test-junit5"))
                implementation(project.dependencies.project(":lib-gradle-test"))
                implementation("org.assertj:assertj-core:3.21.0")
            }
        }
    }
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
