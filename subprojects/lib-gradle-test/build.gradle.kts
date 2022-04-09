plugins {
    kotlin("jvm") version "1.5.31"
    id("com.nisecoder.convention.idea")
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        apiVersion = "1.5"
        languageVersion = "1.5"

        jvmTarget = JavaVersion.VERSION_11.toString()

        javaParameters = true
    }
}
