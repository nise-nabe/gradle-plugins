package com.nisecoder.gradle.plugin.intellij.plugin.portal

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class UpdatePluginsXmlTask: DefaultTask() {
    @get:InputFile
    abstract val pluginXml: RegularFileProperty

    @get:OutputFile
    abstract val updatePluginsXml: RegularFileProperty

    @get:Internal
    val parser: IdeaPluginXmlParser = IdeaPluginXmlParser()

    @TaskAction
    fun updatePluginXml() {

        val plugin = parser.readPluginXml(pluginXml.get().asFile)

        logger.lifecycle("${plugin.id}")
    }
}
