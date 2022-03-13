@file:Suppress("unused")

package com.nisecoder.gradle.plugin.test

import org.intellij.lang.annotations.Language
import java.io.File


fun File.writeKotlin(@Language("gradle.kts") src: String) {
    writeText(src)
}

fun File.writeGroovy(@Language("Groovy") src: String) {
    writeText(src)
}

fun File.writeXml(@Language("XML") src: String) {
    writeText(src)
}
