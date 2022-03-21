package com.nisecoder.gradle.plugin.node

import java.nio.file.Path

data class NodeBinary(
    val installDir: Path,
    val node: Path,
    val npm: Path,
    val npx: Path,
)
