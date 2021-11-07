package com.nisecoder.gradle.plugin.intellij.plugin.portal.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class IdeaVersion(
    @JacksonXmlProperty(isAttribute = true)
    val sinceBuild: String,
    @JacksonXmlProperty(isAttribute = true)
    val untilBuild: String,
)
