package com.nisecoder.gradle.plugin.node

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
        val nodeVersion: Property<String>
        val nodeBinaryType: Property<NodeBinaryType>
    }

    fun provision(nodeCachePath: Path): Path {
        val fileName = parameters.nodeBinaryType.get().let {
            val osName = it.osName
            val arch = it.arch
            val ext = it.ext
            "node-${parameters.nodeVersion.get()}-$osName-$arch.$ext"
        }

        val uri = URI.create("https://nodejs.org/dist/${parameters.nodeVersion.get()}/$fileName")
        val request = HttpRequest.newBuilder(uri).GET().build()
        val client = HttpClient.newHttpClient()
        val dist = nodeCachePath.resolve(fileName)

        if (!dist.toFile().exists()) {
            client.send(request, HttpResponse.BodyHandlers.ofFile(dist))
        }
        return dist
    }

}
