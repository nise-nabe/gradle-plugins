package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.node.NodeService
import com.nisecoder.gradle.plugin.node.NodeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.registerIfAbsent

@Suppress("unused")
class NodePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        val serviceProvider = gradle.sharedServices.registerIfAbsent("node", NodeService::class) {
            maxParallelUsages.set(1)
        }

        tasks.register<NodeTask>("node") {
            nodeService.set(serviceProvider)
        }
    }
}
