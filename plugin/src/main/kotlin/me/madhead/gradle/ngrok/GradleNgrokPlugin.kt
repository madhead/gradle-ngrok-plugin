package me.madhead.gradle.ngrok

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleNgrokPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'me.madhead.gradle.ngrok'")
            }
        }
    }
}
