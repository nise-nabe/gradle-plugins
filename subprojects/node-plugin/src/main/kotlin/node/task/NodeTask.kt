package com.nisecoder.gradle.plugin.node.task

import com.nisecoder.gradle.plugin.node.NodeProvisioningService
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class NodeTask: Exec() {
    @get:Internal
    abstract val nodeProvisioningService: Property<NodeProvisioningService>

    @get:Inject
    abstract val fileOperations: FileOperations

    @get:Internal
    abstract val nodeVersion: Property<String>

    @TaskAction
    override fun exec() {
        val node = nodeProvisioningService.get().provision(fileOperations, nodeVersion.get())

        commandLine(node.node, "-v")

        super.exec()
    }
}
