plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
}

group = "com.nisecoder.gradle.plugin"

subprojects {
    apply(plugin = "maven-publish")

    // inject in GitHub Action Publish Workflow
    val publishVersion: String? by project
    version = if (publishVersion?.isNotEmpty() == true) {
        publishVersion!!.replaceFirst("refs/tags/v", "")
    } else {
        "1.0-SNAPSHOT"
    }

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
