package com.nisecoder.gradle.plugin.github.task

import org.gradle.api.tasks.TaskAction

abstract class PublishToGitHubPackages: AbstractPublishToGitHub() {
    @TaskAction
    fun publish() {

    }
}
