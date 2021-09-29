plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")
}

gradlePlugin {
    plugins {
        register("ci-detect") {
            id = "com.nisecoder.ci-detect"
            implementationClass = "com.nisecoder.gradle.plugin.CiDetectPlugin"
            description = "detect CI services for each CI build logic"
        }
    }
}

pluginBundle {
    website = "https://github.com/nise-nabe/gradle-plugins"
    vcsUrl = "https://github.com/nise-nabe/gradle-plugins"

    (plugins) {
        "ci-detect" {
            tags = listOf("ci")
        }
    }
}
