package com.nisecoder.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

class GitHubPublishPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(PublishingPlugin::class)

        // affect to `maven-publish` plugin
        plugins.withType<MavenPublishPlugin> {
            tasks.register<DefaultTask>("publishToGitHubPackages") {
                description = "Publishes publications to GitHub Packages"
                group = PublishingPlugin.PUBLISH_TASK_GROUP

                dependsOn(tasks.getByName("publish"))
            }
        }

        tasks.register<DefaultTask>("publishToGitHubRelease") {
            description = "Publishes publications to GitHub Release"
            group = PublishingPlugin.PUBLISH_TASK_GROUP

            dependsOn(tasks.getByName("publish"))
        }
    }
}
