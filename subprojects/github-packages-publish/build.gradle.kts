plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

dependencies {
}

gradlePlugin {
    plugins {
        register("github-packages-publish") {
            id = "com.nisecoder.gradle.github-packages-publish"
            implementationClass = "com.nisecoder.gradle.GitHubPackagesPublishPlugin"
        }
    }
}
