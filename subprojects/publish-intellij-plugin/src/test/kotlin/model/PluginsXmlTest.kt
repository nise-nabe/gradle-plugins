package com.nisecoder.gradle.plugin.model

import com.nisecoder.gradle.plugin.intellij.plugin.portal.IdeaPluginXmlParser
import com.nisecoder.gradle.plugin.intellij.plugin.portal.model.IdeaVersion
import com.nisecoder.gradle.plugin.intellij.plugin.portal.model.PluginsXml
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URL
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PluginsXmlTest {
    lateinit var ideaPluginXmlParser: IdeaPluginXmlParser

    @BeforeEach
    fun setUp() {
        ideaPluginXmlParser = IdeaPluginXmlParser()
    }

    @Test
    fun deserialize() {
        // language=xml
        val pluginsXml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <!--
              The <plugins> element contains the description of the plugins available at this repository. Required.
            -->
            <plugins>
              <!--
                Each <plugin> element describes one plugin in the repository. Required.
                id - used by JetBrains IDEs to uniquely identify a plugin. Required. Must match <id> in plugin.xml
                url - path to download the plugin JAR/ZIP file. Required. Must be HTTPS
                version - version of this plugin. Required. Must match <version> in plugin.xml
              -->
              <plugin id="fully.qualified.id.of.this.plugin" url="https://www.mycompany.com/my_repository/mypluginname.jar" version="major.minor.update">
                <!--
                  The <idea-version> element must match the same element in plugin.xml. Required.
                -->
                <idea-version since-build="181.3" until-build="191.*" />
              </plugin>
              <plugin id="id.of.different.plugin" url="https://www.otherserver.com/other_repository/differentplugin.jar" version="major.minor">
                <idea-version since-build="181.3" until-build="191.*" />
              </plugin>
              <plugin id="id.of.no.idea.version.plugin" url="https://www.otherserver.com/other_repository/differentplugin.jar" version="major.minor">
              </plugin>
            </plugins>
        """.trimIndent()


        val result = ideaPluginXmlParser.readPluginsXml(pluginsXml)

        assertEquals("fully.qualified.id.of.this.plugin", result.plugin[0].id)
        assertEquals(URL("https://www.mycompany.com/my_repository/mypluginname.jar"), result.plugin[0].url)
        assertEquals("major.minor.update", result.plugin[0].version)
        assertEquals("181.3", result.plugin[0].ideaVersion!!.sinceBuild)
        assertEquals("191.*", result.plugin[0].ideaVersion!!.untilBuild)

        assertEquals("id.of.no.idea.version.plugin", result.plugin[2].id)
        assertNull(result.plugin[2].ideaVersion)
    }

    @Test
    fun serialize() {
        val model = PluginsXml(
            plugin = mutableListOf(
                PluginsXml.Plugin(
                    id = "fully.qualified.id.of.this.plugin",
                    url = URL("https://www.mycompany.com/my_repository/mypluginname.jar"),
                    version = "major.minor.update",
                    ideaVersion = IdeaVersion(
                        sinceBuild = "181.3",
                        untilBuild = "191.*"
                    )
                ),
                PluginsXml.Plugin(
                    id = "id.of.different.plugin",
                    url = URL("https://www.otherserver.com/other_repository/differentplugin.jar"),
                    version = "major.minor",
                    ideaVersion = IdeaVersion(
                        sinceBuild = "181.3",
                        untilBuild = "191.*"
                    )
                ),
                PluginsXml.Plugin(
                    id = "id.of.no.idea.version.plugin",
                    url = URL("https://www.otherserver.com/other_repository/differentplugin.jar"),
                    version = "major.minor",
                ),
            )
        )
        val str = ideaPluginXmlParser.convertPluginsXml(model)

        assertThat(str)
            .contains("<?xml version='1.0' encoding='UTF-8'?>")

    }
}
