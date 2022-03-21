package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.node.NodeBinaryTypeSelector
import com.nisecoder.gradle.plugin.node.NodeProvisioningService
import com.nisecoder.gradle.plugin.node.NodeTask
import com.nisecoder.gradle.plugin.node.YarnService
import com.nisecoder.gradle.plugin.node.YarnTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.registerIfAbsent

@Suppress("unused")
class NodePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
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
            register<NodeTask>("node") {
                nodeProvisioningService.set(nodeProvisioningServiceProvider)
                nodeVersion.set("v16.14.0")
            }

            register<YarnTask>("yarn") {
                yarnService.set(yarnServiceProvider)
            }
        }
    }
}
