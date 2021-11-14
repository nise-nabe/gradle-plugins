package com.nisecoder.gradle.plugin.task

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

abstract class GitHubReleaseUploadTask: DefaultTask() {
    @get:Input
    abstract val githubToken: Property<String>
    @get:Input
    abstract val githubRepository: Property<String>
    @get:Input
    abstract val releaseName: Property<String>
    @get:InputFile
    abstract val releaseFile: RegularFileProperty

    companion object {
        private val mapper: ObjectMapper = jacksonObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    }

    data class Release(
        val id: Int,
        val assetsUrl: URL,
        val tagName: String,
    )


    @TaskAction
    fun upload() {
        val releaseListResult = HttpClient.newHttpClient().send(HttpRequest.newBuilder().apply {
            GET()
            header("Authorization", "token ${githubToken.get()}")
            uri(URI("https://api.github.com/repos/${ githubRepository.get() }/releases"))
        }.build(), HttpResponse.BodyHandlers.ofString()).body()
        val release = mapper.readValue<List<Release>>(releaseListResult).find { it.tagName == releaseName.get() } ?: throw GradleException("Release not found")

        val uploadUrl = URI("https://uploads.github.com/repos/${ githubRepository.get() }/releases/${ release.id }/assets?name=${ releaseFile.get().asFile.name }")
        logger.info("upload to $uploadUrl")
        val result = HttpClient.newHttpClient().send(HttpRequest.newBuilder().apply {
            header("Authorization", "token ${githubToken.get()}")
            header("Content-Type", "application/zip")
            POST(HttpRequest.BodyPublishers.ofFile(releaseFile.get().asFile.toPath()))
            uri(uploadUrl)
        }.build(), HttpResponse.BodyHandlers.ofString())

        if (result.statusCode() / 100 != 2) {
            throw GradleException("upload is not success: $result, ${result.body()}")
        }
        logger.info("upload success")
    }
}
