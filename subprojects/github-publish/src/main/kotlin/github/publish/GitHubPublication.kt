package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.publish.Publication

interface GitHubPublication: Publication {
    fun artifact(source: Any): GitHubReleaseArtifact
}
