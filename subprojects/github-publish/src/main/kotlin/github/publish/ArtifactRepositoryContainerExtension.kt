package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.Action
import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.gitHubRelease(action: Action<GitHubReleaseRepository>): GitHubReleaseRepository {
    val repository = GitHubReleaseRepository("gitHubRelease").apply {
        action.execute(this)
    }
    add(repository)
    return repository
}
