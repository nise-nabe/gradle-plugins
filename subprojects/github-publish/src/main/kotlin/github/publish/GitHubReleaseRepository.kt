package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.Action
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.AuthenticationContainer
import org.gradle.api.artifacts.repositories.RepositoryContentDescriptor
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ProviderFactory
import org.gradle.internal.reflect.Instantiator
import java.net.URI

class GitHubReleaseRepository(
    private var name: String,
    instantiator: Instantiator,
    authenticationContainer: AuthenticationContainer,
    objectFactory: ObjectFactory,
    providerFactory: ProviderFactory
): ArtifactRepository, AuthenticationSupportedSupport(instantiator, authenticationContainer, objectFactory, providerFactory) {
    lateinit var url: URI

    override fun getName() = name
    override fun setName(name: String) { this.name = name }

    override fun content(configureAction: Action<in RepositoryContentDescriptor>) {
        TODO("Not yet implemented")
    }
}
