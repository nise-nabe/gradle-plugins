package com.nisecoder.gradle.plugin.github.publish

class DefaultGitHubPublication(
    private val name: String,
): GitHubPublication {
    private var withBuildIdentifier = false

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
