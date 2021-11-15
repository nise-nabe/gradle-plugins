package com.nisecoder.gradle.plugin.github.api.payload

import java.net.URL

data class Release(
    val id: Int,
    val assetsUrl: URL,
    val tagName: String,
)
