package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.Action
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.RepositoryContentDescriptor
import org.gradle.api.artifacts.repositories.UrlArtifactRepository

class GitHubReleaseRepository(
    private var name: String
): ArtifactRepository {
    var url: String = ""
    private val urlArtifactRepository: UrlArtifactRepository? = null

    override fun getName() = name
    override fun setName(name: String) { this.name = name }

    override fun content(configureAction: Action<in RepositoryContentDescriptor>) {
        TODO("Not yet implemented")
    }
}
