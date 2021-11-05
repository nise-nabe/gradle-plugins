plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.gradle-plugin")
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:${libs.versions.idea.ext.get()}")
}

gradlePlugin {
    plugins {
        register("idea-ext-ext") {
            id = "com.nisecoder.idea-ext-ext"
            implementationClass = "com.nisecoder.gradle.plugin.IdeaExtExtPlugin"
            description = "idea-ext extension plugin to use in kotlin build script"
        }
    }
}

pluginBundle {
    (plugins) {
        "idea-ext-ext" {
            tags = listOf("intellij")
        }
    }
}
