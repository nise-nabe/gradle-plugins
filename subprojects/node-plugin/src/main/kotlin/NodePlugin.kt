package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.node.NodeBinaryOsName
import com.nisecoder.gradle.plugin.node.NodeProvisioningService
import com.nisecoder.gradle.plugin.node.NodeTask
import com.nisecoder.gradle.plugin.node.OsDetect
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
        val nodeProvisioningServiceProvider = gradle.sharedServices.registerIfAbsent("nodeProvisioning", NodeProvisioningService::class) {
            parameters {
                nodeVersion.set("v16.14.0")
                osName.set(when {
                    OsDetect.isWindows() -> NodeBinaryOsName.win
                    OsDetect.isMac() -> NodeBinaryOsName.darwin
                    OsDetect.isUnix() -> NodeBinaryOsName.linux
                    else -> throw IllegalStateException("Unsupported OS")
                })
                archName.set("x64")
                ext.set(when {
                    OsDetect.isWindows() -> "zip"
                    OsDetect.isMac() -> "tar.gz"
                    OsDetect.isUnix() -> "tar.xz"
                    else -> throw IllegalStateException("Unsupported OS")
                })
            }
            maxParallelUsages.set(1)
        }
        val yarnServiceProvider = gradle.sharedServices.registerIfAbsent("yarn", YarnService::class) {
            maxParallelUsages.set(1)
        }

        tasks {
            register<NodeTask>("node") {
                nodeProvisioningService.set(nodeProvisioningServiceProvider)
            }

            register<YarnTask>("yarn") {
                yarnService.set(yarnServiceProvider)
            }
        }
    }
}
