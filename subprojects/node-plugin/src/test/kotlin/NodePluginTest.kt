package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.test.writeJson
import com.nisecoder.gradle.plugin.test.writeKotlin
import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertNotNull

internal class NodePluginTest {
    @TempDir
    private lateinit var testProjectDir: File

    private lateinit var settingsFile: File
    private lateinit var buildFile: File

    private lateinit var packageJson: File

    @BeforeEach
    fun setup() {
        settingsFile = testProjectDir.resolve("settings.gradle.kts")
        buildFile = testProjectDir.resolve("build.gradle.kts")
        packageJson = testProjectDir.resolve("package.json")
    }

    @Test
    fun nodeVersion() {
        buildFile.writeKotlin("""
          plugins {
            id("com.nisecoder.node")
          }
        """.trimIndent())

        val buildResult = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("nodeVersion", "--stacktrace")
            .build()

        val taskResult = buildResult.task(":nodeVersion")
        assertNotNull(taskResult)
        assertThat(taskResult.outcome).isIn(TaskOutcome.SUCCESS, TaskOutcome.UP_TO_DATE)
        assertThat(buildResult.output.split(System.lineSeparator())).contains("v16.14.2")
    }

    @Test
    fun npmInstall() {
        buildFile.writeKotlin("""
          plugins {
            id("com.nisecoder.node")
          }
        """.trimIndent())

        packageJson.writeJson("""
            {
              "dependencies": {
              }
            }
            
        """.trimIndent())

        val buildResult = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("npmInstall", "--stacktrace")
            .build()

        val taskResult = buildResult.task(":npmInstall")
        assertNotNull(taskResult)
        assertThat(taskResult.outcome).isIn(TaskOutcome.SUCCESS, TaskOutcome.UP_TO_DATE)
        println(buildResult.output)
    }
}
