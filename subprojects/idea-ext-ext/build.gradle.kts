plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        register("idea-ext-ext") {
            id = "com.nisecoder.gradle.idea-ext-ext"
            implementationClass = "com.nisecoder.gradle.IdeaExtExtPlugin"
        }
    }
}
