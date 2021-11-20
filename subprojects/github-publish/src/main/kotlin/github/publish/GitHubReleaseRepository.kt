package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.Action
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.RepositoryContentDescriptor

class GitHubReleaseRepository(
    private var name: String
): ArtifactRepository {
    lateinit var url: String

    override fun getName() = name
    override fun setName(name: String) { this.name = name }

    override fun content(configureAction: Action<in RepositoryContentDescriptor>) {
        TODO("Not yet implemented")
    }
}
