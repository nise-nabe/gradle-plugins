package com.nisecoder.gradle.plugin.intellij.plugin.portal.task

import com.nisecoder.gradle.plugin.intellij.plugin.portal.IdeaPluginXmlParser
import com.nisecoder.gradle.plugin.intellij.plugin.portal.model.PluginsXml
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.net.URI

abstract class UpdatePluginsXmlTask: DefaultTask() {
    @get:InputFile
    abstract val pluginXml: RegularFileProperty
    @get:Input
    abstract val pluginUrl: Property<URI>

    @get:OutputFile
    abstract val updatePluginsXml: RegularFileProperty

    @get:Internal
    val parser: IdeaPluginXmlParser = IdeaPluginXmlParser()

    @TaskAction
    fun updatePluginXml() {
        val plugin = parser.readPluginXml(pluginXml.get().asFile)

        val plugins = parser.readPluginsXml(updatePluginsXml.get().asFile)

        plugins.plugin.find { it.id == plugin.id }?.let {
            pluginUrl.orNull?.run { it.url = this.toURL() }
            it.version = plugin.version
        } ?: plugins.plugin.add(PluginsXml.Plugin(
            id = plugin.id,
            name = project.name,
            url = pluginUrl.get().toURL(),
            version = plugin.version,
        ))

        parser.writePluginsXml(updatePluginsXml.get().asFile, plugins)
    }
}
