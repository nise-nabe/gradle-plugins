package com.nisecoder.gradle.plugin.intellij.plugin.portal.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "plugins")
data class PluginsXml(
    @get:JacksonXmlElementWrapper(useWrapping = false)
    val plugin: MutableList<Plugin> = mutableListOf()
) {
    data class Plugin(
        @JacksonXmlProperty(isAttribute = true)
        val id: String,
        @JacksonXmlProperty(isAttribute = true)
        var url: String,
        @JacksonXmlProperty(isAttribute = true)
        var version: String,

        val name: String? = null,
        val ideaVersion: IdeaVersion? = null
    )
}
