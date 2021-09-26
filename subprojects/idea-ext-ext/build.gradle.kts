plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:${libs.versions.idea.ext.get()}")
}

gradlePlugin {
    plugins {
        register("idea-ext-ext") {
            id = "com.nisecoder.idea-ext-ext"
            implementationClass = "com.nisecoder.gradle.plugin.IdeaExtExtPlugin"
        }
    }
}
