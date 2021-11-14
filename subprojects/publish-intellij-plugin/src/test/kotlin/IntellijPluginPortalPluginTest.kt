package com.nisecoder.gradle.plugin

import com.nisecoder.gradle.plugin.test.writeKotlin
import com.nisecoder.gradle.plugin.test.writeXml
import org.assertj.core.api.Assertions
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertNotNull

class IntellijPluginPortalPluginTest {
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
              id("com.nisecoder.intellij-plugin.portal")
            }
            intellijPortal {
                pluginUrl.set(uri("https://exmaple.com"))
            }
            
        """.trimIndent())

        testProjectDir.resolve("updatePlugins.xml").apply {
            writeXml("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <plugins>
                </plugins>
            """.trimIndent())
        }

        testProjectDir.resolve("src/main/resources/META-INF").apply {
            if (!exists()) mkdirs()
        }.resolve("plugin.xml").apply {
            writeXml("""
                <?xml version="1.0" encoding="UTF-8" ?>
                <plugin>
                    <id>com.example.plugin</id>
                    <name>Example Plugin</name>
                    <version>1.0</version>
                    <vendor>example name</vendor>
                    <change-notes>change note</change-notes>
                </plugin>
            """.trimIndent())
        }


        val buildResult = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("updatePluginXml")
            .build()

        val taskResult = buildResult.task(":updatePluginXml")
        assertNotNull(taskResult)
        Assertions.assertThat(taskResult.outcome).isIn(TaskOutcome.SUCCESS, TaskOutcome.UP_TO_DATE)
        Assertions.assertThat(buildResult.output).contains("")

    }
}
