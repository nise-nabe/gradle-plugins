package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.NamedDomainObjectFactory
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance

class GitHubPublicationFactory(
    private val objectFactory: ObjectFactory
): NamedDomainObjectFactory<GitHubPublication> {
    /**
     * Creates a new object with the given name.
     *
     * @param name The name
     * @return The object.
     */
    override fun create(name: String): GitHubPublication {
        return objectFactory.newInstance(GitHubPublication::class)
    }
}
