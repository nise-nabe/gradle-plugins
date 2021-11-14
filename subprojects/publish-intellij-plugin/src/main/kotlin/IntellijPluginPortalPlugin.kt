package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.intellij.plugin.portal.IntellijPluginPortalExtension
import com.nisecoder.gradle.plugin.intellij.plugin.portal.task.UpdatePluginsXmlTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register

class IntellijPluginPortalPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(JavaPlugin::class)

        // setup configurations
        val portal = extensions.create<IntellijPluginPortalExtension>("intellijPortal")
        portal.updatePluginsXml.convention(layout.file(provider { file("updatePlugins.xml") }))

        // setup tasks
        tasks.register<UpdatePluginsXmlTask>("updatePluginXml") {
            // search plugin.xml from META-INF directory
            pluginXml.set(layout.buildDirectory.file("resources/main/META-INF/plugin.xml"))
            updatePluginsXml.set(portal.updatePluginsXml)
            pluginUrl.set(portal.pluginUrl)

            // execute after copy from resources to build classpath
            dependsOn(tasks.getByName(JavaPlugin.PROCESS_RESOURCES_TASK_NAME))
        }
    }
}
