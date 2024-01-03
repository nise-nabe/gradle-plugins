package com.nisecoder.convention

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
                implementation(gradleTestKit())
                implementation("org.jetbrains.kotlin:kotlin-test-junit5")
                implementation(project(":lib-gradle-test"))
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
