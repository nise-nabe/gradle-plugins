package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.github.publish.GitHubPublication
import com.nisecoder.gradle.plugin.github.publish.GitHubPublicationFactory
import com.nisecoder.gradle.plugin.github.task.PublishToGitHubRelease
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

class GitHubPublishPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(PublishingPlugin::class)

        configure<PublishingExtension> {
            publications.registerFactory(GitHubPublication::class.java, GitHubPublicationFactory(objects))
            val gitHubPublications = publications.withType<GitHubPublication>()

            gitHubPublications.all {
                val targetPublication = this
                tasks.register<PublishToGitHubRelease>("publishToGitHubRelease") {
                    description = "Publishes publications to GitHub Release"
                    group = PublishingPlugin.PUBLISH_TASK_GROUP

                    publication = targetPublication

                    dependsOn(tasks.getByName("publish"))
                }
            }
        }

        // affect to `maven-publish` plugin
        plugins.withType<MavenPublishPlugin> {
            configure<PublishingExtension> {
                val mavenPublications = publications.withType<MavenPublication>()

                mavenPublications.all {
                    val mavenPublication = this
                    tasks.register<PublishToMavenRepository>("publishToGitHubPackages") {
                        description = "Publishes publications to GitHub Packages"
                        group = PublishingPlugin.PUBLISH_TASK_GROUP

                        publication = mavenPublication

                        dependsOn(tasks.getByName("publish"))
                    }
                }
            }
        }
    }
}
