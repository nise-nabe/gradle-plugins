plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

gradlePlugin {
    plugins {
        register("node-plugin") {
            id = "com.nisecoder.node"
            implementationClass = "com.nisecoder.gradle.plugin.NodePlugin"
            description = "convention plugin for node.js integration"
        }
    }
}
