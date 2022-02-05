package com.nisecoder.convention

import org.jetbrains.gradle.ext.settings
import org.jetbrains.gradle.ext.packagePrefix

plugins {
    idea
    id("org.jetbrains.gradle.plugin.idea-ext")
}

idea {
    module {
        settings {
            packagePrefix["src/main/kotlin"] = "com.nisecoder.gradle.plugin"
            packagePrefix["src/test/kotlin"] = "com.nisecoder.gradle.plugin"
        }
    }
}
