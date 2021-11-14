package com.nisecoder.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.register

class GitHubPublishPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(PublishingPlugin::class)

        tasks.register<DefaultTask>("publishToGitHubPackages") {
            description = "Publishes publications to GitHub Packages"
            group = PublishingPlugin.PUBLISH_TASK_GROUP

            dependsOn(tasks.getByName("publish"))
        }
        tasks.register<DefaultTask>("publishToGitHubRelease") {
            description = "Publishes publications to GitHub Release"
            group = PublishingPlugin.PUBLISH_TASK_GROUP

            dependsOn(tasks.getByName("publish"))
        }
    }
}
