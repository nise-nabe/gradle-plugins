import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm")
    id("com.nisecoder.convention.idea")
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

kotlin {
    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_1_9
        languageVersion = KotlinVersion.KOTLIN_1_9
        javaParameters = true
    }
}