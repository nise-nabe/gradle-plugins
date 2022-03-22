package com.nisecoder.gradle.plugin.node.task

import org.gradle.api.tasks.TaskAction

abstract class NpmVersionTask: NodeTask() {
    @TaskAction
    override fun exec() {
        commandLine(npm, "-v")

        super.exec()
    }
}
