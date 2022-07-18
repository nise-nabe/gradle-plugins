plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

gradlePlugin {
    plugins {
        create("ci-detect") {
            id = "com.nisecoder.ci-detect"
            implementationClass = "com.nisecoder.gradle.plugin.CiDetectPlugin"
            description = "detect CI services for each CI build logic"
        }
    }
}

pluginBundle {
    pluginTags = mapOf(
        "ci-detect" to listOf("ci")
    )
}
