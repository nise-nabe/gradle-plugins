plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
}

gradlePlugin {
    plugins {
        create("intellij-plugin-portal") {
            id = "com.nisecoder.intellij-plugin.portal"
            implementationClass = "com.nisecoder.gradle.plugin.IntellijPluginPortalPlugin"
            description = "generate updatePlugins.xml plugin"
        }
    }
}

pluginBundle {
    tags = listOf("intellij")
}
