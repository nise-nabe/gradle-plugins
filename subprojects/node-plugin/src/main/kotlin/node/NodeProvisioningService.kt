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
        val installationDirName = parameters.nodeBinaryType.get().let {
            val osName = it.osName
            val arch = it.arch
            "node-$nodeVersion-$osName-$arch"
        }
        val fileName = parameters.nodeBinaryType.get().let {
            val osName = it.osName
            val arch = it.arch
            val ext = it.ext
            "node-$nodeVersion-$osName-$arch.$ext"
        }

        val installationDir = nodeCacheDir.resolve(installationDirName).toPath()
        if (!installationDir.toFile().exists()) {
            val dist = nodeCacheDir.resolve(fileName)
            val uri = URI.create("https://nodejs.org/dist/$nodeVersion/$fileName")
            val request = HttpRequest.newBuilder(uri).GET().build()
            val client = HttpClient.newHttpClient()
            client.send(request, HttpResponse.BodyHandlers.ofFile(dist.toPath()))
            fileOperations.unpack(dist.toPath(), nodeCacheDir.toPath())
        }

        return NodeBinary(
            resolveExecutable(installationDir),
            resolveNpm(installationDir),
            resolveNpx(installationDir),
            installationDir
        )
    }

    private fun FileOperations.unpack(archiveFile: Path, installationDir: Path): Path {
        val ext = parameters.nodeBinaryType.get().ext
        val fileTree: FileTree = if (ext == "zip") {
            zipTree(archiveFile)
        } else {
            tarTree(archiveFile)
        }
        copy {
            from(fileTree)
            into(installationDir)
        }
        return installationDir
    }

    private fun resolveExecutable(path: Path): Path {
        return if (parameters.nodeBinaryType.get().osName == "win") {
            path.resolve("node.exe")
        } else {
            path.resolve("bin").resolve("node")
        }
    }

    private fun resolveNpm(path: Path): Path {
        return if (parameters.nodeBinaryType.get().osName == "win") {
            path.resolve("npm.cmd")
        } else {
            path.resolve("bin").resolve("npm")
        }
    }

    private fun resolveNpx(path: Path): Path {
        return if (parameters.nodeBinaryType.get().osName == "win") {
            path.resolve("npx.cmd")
        } else {
            path.resolve("bin").resolve("npx")
        }
    }
}
