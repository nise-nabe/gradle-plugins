package com.nisecoder.gradle.plugin.node

import java.nio.file.Path

data class NodeBinary(
    val node: Path,
    val installDir: Path,
)
