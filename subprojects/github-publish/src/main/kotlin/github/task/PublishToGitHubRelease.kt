package com.nisecoder.gradle.plugin.github.task

import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

@DisableCachingByDefault
abstract class PublishToGitHubRelease: AbstractPublishToGitHub() {
    @TaskAction
    fun publish() {

    }
}
