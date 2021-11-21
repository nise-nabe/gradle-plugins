package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.github.publish.GitHubPublication
import com.nisecoder.gradle.plugin.github.publish.GitHubPublicationFactory
import com.nisecoder.gradle.plugin.github.publish.GitHubReleaseRepository
import com.nisecoder.gradle.plugin.github.task.PublishToGitHubRelease
import org.gradle.api.NamedDomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class GitHubPublishPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(PublishingPlugin::class)

        configure<PublishingExtension> {
            publications.registerFactory(GitHubPublication::class.java, GitHubPublicationFactory(objects))
            val gitHubPublications = publications.withType<GitHubPublication>()
            val gitHubReleaseRepositories = repositories.withType<GitHubReleaseRepository>()

            gitHubPublications.all {
                tasks.registerGitHubReleaseTasks(this, gitHubReleaseRepositories)
            }
        }
    }

    private fun TaskContainer.registerGitHubReleaseTasks(
        targetPublication: GitHubPublication,
        gitHubReleaseRepository: NamedDomainObjectCollection<GitHubReleaseRepository>,
    ) {
        gitHubReleaseRepository.all {
            val repository = this
            register<PublishToGitHubRelease>("publishToGitHubRelease") {
                description = "Publishes publications to GitHub Release"
                group = PublishingPlugin.PUBLISH_TASK_GROUP

                publication = targetPublication
                githubRepository.set(repository.url.toString())

                dependsOn(named("publish"))
            }
        }
    }
}
