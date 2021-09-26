plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
}

group = "com.nisecoder.gradle.plugin"
version = "0.0.1"

subprojects {
    apply(plugin = "maven-publish")

    extensions.configure<PublishingExtension> {
        repositories {
            maven {
                name = "gitHubPackages"
                url = uri("https://maven.pkg.github.com/nise-nabe/gradle-plugins")
                // set ~/.gradle/gradle.properties
                // gitHubPackagesUsername
                // gitHubPackagesPassword
                credentials(PasswordCredentials::class)
            }
        }
    }
}
