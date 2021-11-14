package com.nisecoder.gradle.plugin.github

class DefaultGitHubPublication: GitHubPublication {
    /**
     * The object's name.
     *
     *
     * Must be constant for the life of the object.
     *
     * @return The name. Never null.
     */
    override fun getName(): String {
        TODO("Not yet implemented")
    }

    /**
     * Disables publication of a unique build identifier in Gradle Module Metadata.
     *
     *
     * The build identifier is not published by default.
     *
     * @since 6.6
     */
    override fun withoutBuildIdentifier() {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }
}
