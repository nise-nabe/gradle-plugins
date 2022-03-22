package com.nisecoder.gradle.plugin.node

import org.gradle.api.provider.Property


interface NodeExtension {
    val version: Property<String>
}
