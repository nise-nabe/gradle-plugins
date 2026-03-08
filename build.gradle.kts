plugins {
    id("com.nisecoder.convention.root")
}

subprojects {
    group = "com.nisecoder.gradle.plugin"

    // inject in GitHub Action Publish Workflow
    val publishVersion: String? by project
    version =
        if (publishVersion?.isNotEmpty() == true) {
            publishVersion!!.replaceFirst("refs/tags/v", "")
        } else {
            "1.0-SNAPSHOT"
        }
}
