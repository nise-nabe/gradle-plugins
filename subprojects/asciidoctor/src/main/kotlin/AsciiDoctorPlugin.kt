package com.nisecoder.gradle.plugin

import org.asciidoctor.gradle.jvm.AsciidoctorJPlugin
import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName

class AsciiDoctorPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(AsciidoctorJPlugin::class)

        tasks.getByName<AsciidoctorTask>("asciidoctor") {
            // collect into root buildDirectory for publishing to GitHub Pages
            val outputDir = if (project == rootProject) {
                rootProject.layout.buildDirectory.dir("docs/asciidoc")
            } else {
                rootProject.layout.buildDirectory.dir("docs/asciidoc/${project.name}")
            }
            setOutputDir(outputDir)
        }
    }
}
