package com.nisecoder.gradle.plugin.node

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

abstract class YarnService: BuildService<YarnService.Params> {
    interface Params: BuildServiceParameters

    fun exec() {
        val process = ProcessBuilder("yarn")
        process.start()
    }
}
