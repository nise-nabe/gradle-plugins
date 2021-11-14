package com.nisecoder.gradle.plugin.github.task

import com.nisecoder.gradle.plugin.github.GitHubPublication
import org.gradle.api.DefaultTask
import org.gradle.api.credentials.Credentials
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Nested

abstract class AbstractPublishToGitHub: DefaultTask() {
    @get:Nested
    abstract val credentials: Property<Credentials>

    @get:Internal
    abstract var publication: GitHubPublication
}
