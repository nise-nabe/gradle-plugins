plugins {
    kotlin("jvm")
    id("com.nisecoder.convention.idea")
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        apiVersion = "1.7"
        languageVersion = "1.7"

        jvmTarget = JavaVersion.VERSION_11.toString()

        javaParameters = true
    }
}
