package com.nisecoder.gradle.plugin.node

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Path

abstract class NodeTask: DefaultTask() {
    @TaskAction
    fun exec() {
        val uri = URI.create("https://nodejs.org/dist/v16.14.0/node-v16.14.0-win-x64.zip")
        val request = HttpRequest.newBuilder(uri).GET().build()
        val client = HttpClient.newHttpClient()
        val dist = Path.of(".").resolve("node-v16.14.0-win-x64.zip")

        client.send(request, HttpResponse.BodyHandlers.ofFile(dist))

        logger.lifecycle("Downloaded ${dist.toAbsolutePath()}")
    }
}
