package com.nisecoder.gradle.plugin.node

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileTree
import org.gradle.api.internal.file.FileOperations
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

    fun provision(fileOperations: FileOperations, nodeVersion: String): NodeBinary {
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

        val unpacked = fileOperations.unpack(dist.toPath())

        return NodeBinary(resolveExecutable(unpacked), unpacked)
    }

    private fun FileOperations.unpack(path: Path): Path {
        val fileTree: FileTree = if (parameters.nodeBinaryType.get().ext == "zip") {
            zipTree(path)
        } else {
            tarTree(path)
        }
        val installationDir = path.parent.toFile()
        copy {
            from(fileTree)
            into(installationDir)
        }
        val unpackedDirName = path.toFile().name.let { it.substring(0, it.lastIndexOf('.')) }
        return installationDir.resolve(unpackedDirName).toPath()
    }

    private fun resolveExecutable(path: Path): Path {
        return if (parameters.nodeBinaryType.get().osName == "win") {
            path.resolve("node.exe")
        } else {
            path.resolve("node")
        }
    }
}
