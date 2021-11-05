plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
    id("com.nisecoder.convention.idea")
    id("com.nisecoder.convention.build-gradle-plugin")
}

dependencies {
    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2")
}

gradlePlugin {
    plugins {
        register("github-pages.asciidoctor") {
            id = "com.nisecoder.github-pages.asciidoctor"
            implementationClass = "com.nisecoder.gradle.plugin.AsciiDoctorPlugin"
            description = "convention plugin for publish asciidoc document to GitHub Pages"
        }
    }
}

pluginBundle {
    (plugins) {
        "github-pages.asciidoctor" {
            tags = listOf("GitHub Pages", "AsciiDoc")
        }
    }
}
