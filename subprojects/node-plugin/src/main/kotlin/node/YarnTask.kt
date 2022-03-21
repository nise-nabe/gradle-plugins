package com.nisecoder.gradle.plugin.node

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

abstract class YarnTask: DefaultTask() {
    @get:Internal
    abstract val yarnService: Property<YarnService>

    @TaskAction
    fun exec() {
        yarnService.get().exec()
    }
}