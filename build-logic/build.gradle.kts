plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:${libs.versions.idea.ext.get()}")
    implementation("com.gradle.publish:plugin-publish-plugin:1.0.0")
}

kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}
