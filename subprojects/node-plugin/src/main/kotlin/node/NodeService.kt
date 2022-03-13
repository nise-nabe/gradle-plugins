package com.nisecoder.gradle.plugin.node

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

abstract class NodeService: BuildService<NodeService.Params> {
    interface Params: BuildServiceParameters

    fun exec() {
        val process = ProcessBuilder("node")
        process.start()
    }
}
