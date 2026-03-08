import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm")
    id("com.nisecoder.convention.idea")
}

dependencies {
    implementation(libs.annotations)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

kotlin {
    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_1_9
        languageVersion = KotlinVersion.KOTLIN_1_9
        javaParameters = true
    }
}
