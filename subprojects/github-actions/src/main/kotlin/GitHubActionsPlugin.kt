package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.github.GitHubActionsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class GitHubActionsPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        extensions.create<GitHubActionsExtension>("githubActions")
    }
}
