package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.test.writeKotlin
import org.assertj.core.api.Assertions
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

    @BeforeEach
    fun setup() {
        settingsFile = testProjectDir.resolve("settings.gradle.kts")
        buildFile = testProjectDir.resolve("build.gradle.kts")
    }

    @Test
    fun test() {
        buildFile.writeKotlin("""
          plugins {
            id("com.nisecoder.node")
          }
        """.trimIndent())

        val buildResult = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("help")
            .build()

        val taskResult = buildResult.task(":help")
        assertNotNull(taskResult)
        Assertions.assertThat(taskResult.outcome).isIn(TaskOutcome.SUCCESS, TaskOutcome.UP_TO_DATE)
    }
}
