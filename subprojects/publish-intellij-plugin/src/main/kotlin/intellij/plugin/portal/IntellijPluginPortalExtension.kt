package com.nisecoder.gradle.plugin.intellij.plugin.portal

import org.gradle.api.file.RegularFileProperty

interface IntellijPluginPortalExtension {
    val updatePluginsXml: RegularFileProperty
}
