package com.nisecoder.gradle.plugin.github.task

import org.gradle.api.DefaultTask
import org.gradle.api.credentials.Credentials
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

abstract class AbstractPublishToGitHub: DefaultTask() {
    @get:Nested
    abstract val credentials: Property<Credentials>
}
