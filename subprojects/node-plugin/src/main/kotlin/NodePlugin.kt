package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.node.NodeBinaryTypeSelector
import com.nisecoder.gradle.plugin.node.NodeExtension
import com.nisecoder.gradle.plugin.node.NodeProvisioningService
import com.nisecoder.gradle.plugin.node.YarnService
import com.nisecoder.gradle.plugin.node.task.NodeVersionTask
import com.nisecoder.gradle.plugin.node.task.NpmInstallTask
import com.nisecoder.gradle.plugin.node.task.NpmVersionTask
import com.nisecoder.gradle.plugin.node.task.YarnTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.registerIfAbsent

@Suppress("unused")
class NodePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        val nodeExtension = extensions.create<NodeExtension>("node").also {
            it.version.convention("v16.14.2")
        }

        val binaryType = NodeBinaryTypeSelector.select()
        logger.debug("os.name = ${NodeBinaryTypeSelector.getOsName()}")
        logger.debug("os.arch = ${NodeBinaryTypeSelector.getOsArch()}")

        val nodeCacheDir = gradle.gradleUserHomeDir.resolve("node")
        val nodeProvisioningServiceProvider = gradle.sharedServices.registerIfAbsent("nodeProvisioning", NodeProvisioningService::class) {
            parameters {
                nodeBinaryType.set(binaryType)
                nodeCachePath.set(nodeCacheDir)
            }
        }
        val yarnServiceProvider = gradle.sharedServices.registerIfAbsent("yarn", YarnService::class) {
            maxParallelUsages.set(1)
        }

        tasks {
            register<NodeVersionTask>("nodeVersion") {
                nodeProvisioningService.set(nodeProvisioningServiceProvider)
                nodeVersion.set(nodeExtension.version)
            }

            register<NpmVersionTask>("npmVersion") {
                nodeProvisioningService.set(nodeProvisioningServiceProvider)
                nodeVersion.set(nodeExtension.version)
            }

            register<NpmInstallTask>("npmInstall") {
                nodeProvisioningService.set(nodeProvisioningServiceProvider)
                nodeVersion.set(nodeExtension.version)
            }

            register<YarnTask>("yarn") {
                yarnService.set(yarnServiceProvider)
            }
        }
    }
}
