package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.task.GitHubReleaseUploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class GitHubReleaseUploadPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        tasks.register<GitHubReleaseUploadTask>("githubReleaseUpload")
    }
}
