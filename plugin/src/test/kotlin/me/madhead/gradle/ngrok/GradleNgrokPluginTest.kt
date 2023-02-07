package me.madhead.gradle.ngrok

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class GradleNgrokPluginTest {
    @Test
    fun `plugin registers task`() {
        val project = ProjectBuilder.builder().build()

        project.plugins.apply("me.madhead.gradle.ngrok")

        assertNotNull(project.tasks.findByName("greeting"))
    }
}
