@file:Suppress("unused")

package com.nisecoder.gradle.plugin.cidetect

val ciService: CiService?
    get() = when {
        isCircleCI -> CiService.CIRCLECI
        isJenkins -> CiService.JENKINS
        isDrone -> CiService.DRONE
        else -> null
    }

val isCircleCI: Boolean
    get() = (System.getenv("CIRCLECI") ?: "") == "true"

val isJenkins: Boolean
    get() = (System.getenv("JENKINS_URL") ?: "") == ""

val isDrone: Boolean
    get() = (System.getenv("DRONE") ?: "") == "true"

val isCI: Boolean
    get() = (System.getenv("CI") ?: "")  == "true"
