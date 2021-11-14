package com.nisecoder.gradle.plugin.intellij.plugin.portal

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.nisecoder.gradle.plugin.intellij.plugin.portal.model.IdeaPluginXml
import com.nisecoder.gradle.plugin.intellij.plugin.portal.model.PluginsXml
import java.io.File

class IdeaPluginXmlParser {
    companion object {
        private val kotlinModule = KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, true)
                .configure(KotlinFeature.StrictNullChecks, true)
                .build()
        private val mapper = XmlMapper()
            .enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION)
            .registerModule(kotlinModule)
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

    fun readPluginXml(file: File): IdeaPluginXml {
        return mapper.readValue(file)
    }

    fun readPluginXml(str: String): IdeaPluginXml {
        return mapper.readValue(str)
    }

    fun readPluginsXml(file: File): PluginsXml {
        return mapper.readValue(file)
    }

    fun readPluginsXml(str: String): PluginsXml {
        return mapper.readValue(str)
    }

    fun writePluginsXml(file: File, xml: PluginsXml) {
        return mapper.writerWithDefaultPrettyPrinter().writeValue(file, xml)
    }

    fun convertPluginsXml(xml: PluginsXml): String {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(xml)
    }
}
