package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.publish.Publication

class GitHubPublication(
    private val name: String,
): Publication {
    private var withBuildIdentifier = false

    fun artifact(source: Any): GitHubReleaseArtifact {
        TODO("Not yet implemented")
    }

    /**
     * The object's name.
     *
     *
     * Must be constant for the life of the object.
     *
     * @return The name. Never null.
     */
    override fun getName() = name

    /**
     * Disables publication of a unique build identifier in Gradle Module Metadata.
     *
     *
     * The build identifier is not published by default.
     *
     * @since 6.6
     */
    override fun withoutBuildIdentifier() {
        withBuildIdentifier = false
    }

    /**
     * Enables publication of a unique build identifier in Gradle Module Metadata.
     *
     *
     * The build identifier is not published by default.
     *
     * @since 6.6
     */
    override fun withBuildIdentifier() {
        withBuildIdentifier = true
    }
}
