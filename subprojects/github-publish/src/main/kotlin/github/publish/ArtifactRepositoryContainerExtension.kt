package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.Action
import org.gradle.api.artifacts.ArtifactRepositoryContainer

fun ArtifactRepositoryContainer.gitHubRelease(action: Action<GitHubReleaseRepository>) {
    add(GitHubReleaseRepository("gitHubRelease").apply {
        action.execute(this)
    })
}
