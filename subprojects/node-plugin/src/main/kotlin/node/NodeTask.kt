package com.nisecoder.gradle.plugin.node

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileTree
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.initialization.GradleUserHomeDirProvider
import org.gradle.process.ExecOperations
import java.io.File
import java.nio.file.Path
import javax.inject.Inject

abstract class NodeTask: DefaultTask() {
    @get:Internal
    abstract val nodeProvisioningService: Property<NodeProvisioningService>

    @get:Inject
    abstract val fileOperations: FileOperations

    @get:Inject
    abstract val execOperations: ExecOperations

    @get:Inject
    abstract val gradleUserHomeDirProvider: GradleUserHomeDirProvider

    @TaskAction
    fun exec() {
        val nodeCacheDir = gradleUserHomeDirProvider.gradleUserHomeDirectory.resolve("node").also {
            if (!it.exists()) {
                it.mkdirs()
                logger.debug("Created node directory: {}", it)
            } else {
                logger.debug("node directory already exists: {}", it)
            }
        }

        val path = nodeProvisioningService.get().provision(nodeCacheDir.toPath())
        val unpacked = unpack(path)

        logger.info("Unpacked node to ${unpacked.absolutePath}")

        execOperations.exec {
            commandLine(unpacked.resolve("node.exe"), "-v")
        }
    }

    private fun unpack(path: Path): File {
        val fileTree: FileTree = fileOperations.zipTree(path)
        val installationDir = path.parent.toFile()
        fileOperations.copy {
            from(fileTree)
            into(installationDir)
        }
        val unpackedDirName = path.toFile().name.let { it.substring(0, it.lastIndexOf('.')) }
        return installationDir.resolve(unpackedDirName)
    }
}
