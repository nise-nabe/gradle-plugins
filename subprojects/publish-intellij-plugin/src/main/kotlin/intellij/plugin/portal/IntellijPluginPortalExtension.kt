package com.nisecoder.gradle.plugin.intellij.plugin.portal

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import java.net.URI

interface IntellijPluginPortalExtension {
    val updatePluginsXml: RegularFileProperty
    val pluginUrl: Property<URI>
}
