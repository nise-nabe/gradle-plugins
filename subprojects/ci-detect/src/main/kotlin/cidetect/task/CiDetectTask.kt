package com.nisecoder.gradle.plugin.cidetect.task

import com.nisecoder.gradle.plugin.cidetect.ciService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

@DisableCachingByDefault(because = "Produces only non-cacheable console output")
abstract class CiDetectTask: DefaultTask() {
    init {
        group = "help"
    }

    @TaskAction
    fun detectCI() {
        println(ciService?.name ?: "NO_CI")
    }
}
