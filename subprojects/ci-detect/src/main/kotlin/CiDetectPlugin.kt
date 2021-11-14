package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.cidetect.task.CiDetectTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class CiDetectPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        tasks.register<CiDetectTask>("detectCi")
    }
}
