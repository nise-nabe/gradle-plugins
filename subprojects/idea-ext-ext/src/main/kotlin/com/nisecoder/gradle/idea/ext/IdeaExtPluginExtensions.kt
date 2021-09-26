@file:Suppress("unused")

package com.nisecoder.gradle.idea.ext

import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugins.ide.idea.model.IdeaModule
import org.gradle.plugins.ide.idea.model.IdeaProject
import org.jetbrains.gradle.ext.ActionDelegationConfig
import org.jetbrains.gradle.ext.CopyrightConfiguration
import org.jetbrains.gradle.ext.EncodingConfiguration
import org.jetbrains.gradle.ext.GroovyCompilerConfiguration
import org.jetbrains.gradle.ext.IdeaCompilerConfiguration
import org.jetbrains.gradle.ext.ModuleSettings
import org.jetbrains.gradle.ext.ModuleTypesConfig
import org.jetbrains.gradle.ext.PackagePrefixContainer
import org.jetbrains.gradle.ext.ProjectSettings
import org.jetbrains.gradle.ext.TaskTriggersConfig


fun IdeaProject.settings(block: ProjectSettings.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ProjectSettings.delegateActions(block: ActionDelegationConfig.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ProjectSettings.taskTriggers(block: TaskTriggersConfig.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ProjectSettings.compiler(block: IdeaCompilerConfiguration.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ProjectSettings.groovyCompiler(block: GroovyCompilerConfiguration.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ProjectSettings.copyright(block: CopyrightConfiguration.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ProjectSettings.encoding(block: EncodingConfiguration.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun IdeaModule.settings(block: ModuleSettings.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

val ModuleSettings.packagePrefix: PackagePrefixContainer
    get() = (this as ExtensionAware).extensions.getByType()

fun ModuleSettings.moduleType(block: ModuleTypesConfig.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)
