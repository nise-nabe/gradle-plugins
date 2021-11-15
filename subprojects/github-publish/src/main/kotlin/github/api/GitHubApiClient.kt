package com.nisecoder.gradle.plugin.github.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nisecoder.gradle.plugin.github.api.payload.Release
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Path

class GitHubApiClient {
    companion object {
        private val mapper: ObjectMapper = jacksonObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    }

    fun fetchReleaseList(
        githubToken: String,
        githubRepository: String,
    ): List<Release> {
        val releaseListResult = HttpClient.newHttpClient().send(HttpRequest.newBuilder().apply {
            GET()
            header("Authorization", "token $githubToken")
            uri(URI("https://api.github.com/repos/$githubRepository/releases"))
        }.build(), HttpResponse.BodyHandlers.ofString()).body()
        return mapper.readValue(releaseListResult)
    }

    /**
     * @throws GitHubApiException if upload was failed
     */
    fun uploadToRelease(
        releaseId: Int,
        githubToken: String,
        githubRepository: String,
        uploadFile: Path,
        uploadFileName: String? = uploadFile.fileName.toString(),
    ) {
        val uploadUrl = URI("https://uploads.github.com/repos/$githubRepository/releases/$releaseId/assets?name=${ uploadFileName }")
        val result = HttpClient.newHttpClient().send(HttpRequest.newBuilder().apply {
            header("Authorization", "token $githubToken")
            header("Content-Type", "application/zip")
            POST(HttpRequest.BodyPublishers.ofFile(uploadFile))
            uri(uploadUrl)
        }.build(), HttpResponse.BodyHandlers.ofString())

        if (result.statusCode() / 100 != 2) {
            throw GitHubApiException("upload is not success: $result, ${result.body()}")
        }
    }
}
