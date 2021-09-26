plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.+")
}

gradlePlugin {
    plugins {
        register("idea-ext-ext") {
            id = "com.nisecoder.gradle.idea-ext-ext"
            implementationClass = "com.nisecoder.gradle.IdeaExtExtPlugin"
        }
    }
}
