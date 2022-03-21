package com.nisecoder.gradle.plugin.node

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Path

abstract class NodeProvisioningService: BuildService<NodeProvisioningService.Params> {
    interface Params: BuildServiceParameters {
        val nodeBinaryType: Property<NodeBinaryType>
        val nodeCachePath: DirectoryProperty
    }

    fun provision(nodeVersion: String): Path {
        val nodeCacheDir = parameters.nodeCachePath.get().asFile.also {
            if (!it.exists()) {
                it.mkdirs()
            }
        }
        val fileName = parameters.nodeBinaryType.get().let {
            val osName = it.osName
            val arch = it.arch
            val ext = it.ext
            "node-$nodeVersion-$osName-$arch.$ext"
        }

        val dist = nodeCacheDir.resolve(fileName)
        if (!dist.exists()) {
            val uri = URI.create("https://nodejs.org/dist/$nodeVersion/$fileName")
            val request = HttpRequest.newBuilder(uri).GET().build()
            val client = HttpClient.newHttpClient()
            client.send(request, HttpResponse.BodyHandlers.ofFile(dist.toPath()))
        }
        return dist.toPath()
    }

}
