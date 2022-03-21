package com.nisecoder.gradle.plugin.node

import org.gradle.api.DefaultTask
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import javax.inject.Inject

abstract class NodeTask: DefaultTask() {
    @get:Internal
    abstract val nodeProvisioningService: Property<NodeProvisioningService>

    @get:Inject
    abstract val fileOperations: FileOperations

    @get:Inject
    abstract val execOperations: ExecOperations

    @get:Internal
    abstract val nodeVersion: Property<String>

    @TaskAction
    fun exec() {
        val node = nodeProvisioningService.get().provision(fileOperations, nodeVersion.get())

        execOperations.exec {
            commandLine(node.node, "-v")
        }
    }
}
