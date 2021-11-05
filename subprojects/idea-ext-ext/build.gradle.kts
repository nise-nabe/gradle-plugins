plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")
    id("com.nisecoder.convention.idea")
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
    website = "https://github.com/nise-nabe/gradle-plugins"
    vcsUrl = "https://github.com/nise-nabe/gradle-plugins"

    (plugins) {
        "idea-ext-ext" {
            tags = listOf("intellij")
        }
    }
}
