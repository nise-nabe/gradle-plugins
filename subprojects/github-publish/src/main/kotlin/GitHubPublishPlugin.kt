package com.nisecoder.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitHubPublishPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        println("Hello, GitHubPublishPlugin!")
    }
}
