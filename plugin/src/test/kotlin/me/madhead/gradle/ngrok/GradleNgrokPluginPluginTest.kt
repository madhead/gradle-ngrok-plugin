/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package me.madhead.gradle.ngrok

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * A simple unit test for the 'me.madhead.gradle.ngrok.greeting' plugin.
 */
class GradleNgrokPluginPluginTest {
    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("me.madhead.gradle.ngrok.greeting")

        // Verify the result
        assertNotNull(project.tasks.findByName("greeting"))
    }
}