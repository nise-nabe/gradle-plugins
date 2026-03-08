plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation(libs.idea.ext)
    implementation(libs.gradlePublishPlugin)
}
