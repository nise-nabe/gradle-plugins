plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

dependencies {
    implementation(libs.asciidoctor)
}

gradlePlugin {
    plugins {
        create("github-pages.asciidoctor") {
            id = "com.nisecoder.github-pages.asciidoctor"
            implementationClass = "com.nisecoder.gradle.plugin.AsciiDoctorPlugin"
            description = "convention plugin for publish asciidoc document to GitHub Pages"
            tags = listOf("GitHub Pages", "AsciiDoc")
        }
    }
}
