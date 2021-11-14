package com.nisecoder.gradle.plugin.github

import org.gradle.api.credentials.Credentials

/**
 * GitHub PAT (Personal Access Token) credentials.
 */
interface GitHubCredentials: Credentials {
    val username: String
    val accessToken: String
}
