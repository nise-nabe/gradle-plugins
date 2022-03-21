package com.nisecoder.gradle.plugin.node.task

import org.gradle.api.tasks.TaskAction

abstract class NpmInstallTask: NodeTask() {
    @TaskAction
    override fun exec() {
        commandLine(npm, "install")

        super.exec()
    }
}
