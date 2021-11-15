package com.nisecoder.gradle.plugin.github.task

import com.nisecoder.gradle.plugin.github.api.GitHubApiClient
import org.gradle.api.GradleException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

@DisableCachingByDefault
abstract class PublishToGitHubRelease: AbstractPublishToGitHub() {
    companion object {
        private val api: GitHubApiClient = GitHubApiClient()
    }

    @get:Input
    abstract val githubToken: Property<String>
    @get:Input
    abstract val githubRepository: Property<String>
    @get:Input
    abstract val releaseName: Property<String>
    @get:InputFile
    abstract val releaseFile: RegularFileProperty

    @TaskAction
    fun publish() {
        val releaseList = api.fetchReleaseList(githubToken.get(), githubRepository.get())

        val release = releaseList.find { it.tagName == releaseName.get()} ?: throw GradleException("Release is not found")

        api.uploadToRelease(release.id, githubToken.get(), githubRepository.get(), releaseFile.get().asFile.toPath())
    }
}
