package com.nisecoder.convention

plugins {
    java
    `maven-publish`
    id("com.gradle.plugin-publish")
}

gradlePlugin {
    website = "https://github.com/nise-nabe/gradle-plugins"
    vcsUrl = "https://github.com/nise-nabe/gradle-plugins"
}

testing {
    @Suppress("UNUSED_VARIABLE", "UnstableApiUsage")
    suites {
        val test by getting(JvmTestSuite::class) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            useJUnitJupiter(libs.findVersion("junit-jupiter").get().requiredVersion)

            dependencies {
                implementation(gradleTestKit())
                implementation(libs.findLibrary("kotlin-test-junit5").get())
                implementation(project(":lib-gradle-test"))
                implementation(libs.findLibrary("assertj-core").get())
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
