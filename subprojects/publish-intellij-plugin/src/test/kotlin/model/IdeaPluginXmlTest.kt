package com.nisecoder.gradle.plugin.model

import com.nisecoder.gradle.plugin.intellij.plugin.portal.IdeaPluginXmlParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IdeaPluginXmlTest {
    @Test
    fun test() {
        // language=xml
        val pluginXml = """
                <?xml version="1.0" encoding="UTF-8" ?>
                <plugin>
                    <id>com.example</id>
                    <name>example name</name>
                    <version>1.0</version>
                    <description><![CDATA[description]]></description>
                    <change-notes>change note</change-notes>
                    <vendor>nise-nabe</vendor>
                </plugin>
        """.trimIndent()

        val ideaPluginXmlParser = IdeaPluginXmlParser()

        val result = ideaPluginXmlParser.readPluginXml(pluginXml)

        assertEquals("com.example", result.id)
        assertEquals("example name", result.name)
        assertEquals("1.0", result.version)
        assertEquals("description", result.description)
        assertEquals("change note", result.changeNotes)
        assertEquals("nise-nabe", result.vendor)
    }
}
