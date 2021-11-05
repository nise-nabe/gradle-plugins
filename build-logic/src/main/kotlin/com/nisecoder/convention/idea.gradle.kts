package com.nisecoder.convention

import org.jetbrains.gradle.ext.ModuleSettings
import org.jetbrains.gradle.ext.PackagePrefixContainer

plugins {
    idea
    id("org.jetbrains.gradle.plugin.idea-ext")
}

idea {
    module {
        (this as ExtensionAware).extensions.configure<ModuleSettings> {
            (this as ExtensionAware).extensions.getByType<PackagePrefixContainer>()["src/main/kotlin"] = "com.nisecoder.gradle.plugin"
        }
    }
}
