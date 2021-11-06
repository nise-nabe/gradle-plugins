package com.nisecoder.gradle.plugin.intellij.plugin.portal.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "idea-plugin")
data class IdeaPluginXml(
    val id: String,
    val name: String,
    val description: String,
    val version: String,
    val vendor: String,
    val changeNotes: String,
)
