package me.madhead.gradle.ngrok

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class GradleNgrokPluginFunctionalTest {
    @TempDir
    lateinit var tempFolder: File

    private fun getProjectDir() = tempFolder
    private fun getBuildFile() = getProjectDir().resolve("build.gradle")
    private fun getSettingsFile() = getProjectDir().resolve("settings.gradle")

    @Test
    fun `can run task`() {
        getSettingsFile().writeText("")
        getBuildFile().writeText(
            """
            plugins {
                id('me.madhead.gradle.ngrok')
            }
            """
        )

        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("greeting")
        runner.withProjectDir(getProjectDir())
        val result = runner.build();

        assertTrue(result.output.contains("Hello from plugin 'me.madhead.gradle.ngrok'"))
    }
}
