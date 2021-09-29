@file:Suppress("unused")

package com.nisecoder.gradle.plugin.cidetect

val ciService: CiService?
    get() = when {
        isCircleCI -> CiService.CIRCLECI
        isJenkins -> CiService.JENKINS
        isDrone -> CiService.DRONE
        isGithubActions -> CiService.GITHUB_ACTIONS
        else -> null
    }

/** https://circleci.com/docs/2.0/env-vars/#built-in-environment-variables */
val isCircleCI: Boolean
    get() = (System.getenv("CIRCLECI") ?: "") == "true"

val isJenkins: Boolean
    get() = (System.getenv("JENKINS_URL") ?: "") == ""

/** https://docs.drone.io/pipeline/environment/reference/drone/ */
val isDrone: Boolean
    get() = (System.getenv("DRONE") ?: "") == "true"

/** https://docs.github.com/en/actions/learn-github-actions/environment-variables#default-environment-variables */
val isGithubActions: Boolean
    get() = (System.getenv("GITHUB_ACTIONS") ?: "") == "true"

val isCI: Boolean
    get() = (System.getenv("CI") ?: "")  == "true"
