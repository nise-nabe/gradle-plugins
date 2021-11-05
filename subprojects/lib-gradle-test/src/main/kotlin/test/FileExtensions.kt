@file:Suppress("unused")

package com.nisecoder.gradle.plugin.test

import org.intellij.lang.annotations.Language
import java.io.File


fun File.writeKotlin(@Language("kotlin") src: String) {
    writeText(src)
}

fun File.writeGroovy(@Language("Groovy") src: String) {
    writeText(src)
}
