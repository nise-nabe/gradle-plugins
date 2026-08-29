import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("com.nisecoder.convention.kotlin-jvm")
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
        apiVersion = KotlinVersion.KOTLIN_2_2
        languageVersion = KotlinVersion.KOTLIN_2_2
        javaParameters = true
    }
}
