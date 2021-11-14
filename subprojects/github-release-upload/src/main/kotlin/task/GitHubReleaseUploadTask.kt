package com.nisecoder.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

abstract class GitHubReleaseUploadTask: DefaultTask() {
    @get:Input
    lateinit var githubToken: Property<String>
    @get:Input
    lateinit var githubRepository: Property<String>
    @get:Input
    lateinit var releaseVersion: Property<String>
    @get:InputFile
    lateinit var releaseFile: RegularFile

    @TaskAction
    fun upload() {
        HttpClient.newHttpClient().send(HttpRequest.newBuilder().apply {
            header("Authorization", "token ${githubToken.get()}")
            header("Content-Type", "application/octet-stream")
            POST(HttpRequest.BodyPublishers.ofFile(releaseFile.asFile.toPath()))
            uri(URI("https://uploads.github.com/repos/${ githubRepository.get() }/releases/${ releaseVersion.get() }/assets?name=${ releaseFile.asFile.name }"))
        }.build(), HttpResponse.BodyHandlers.ofString())
    }
}
