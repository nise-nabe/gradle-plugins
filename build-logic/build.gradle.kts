plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:${libs.versions.idea.ext.get()}")
}
