package me.madhead.gradle.ngrok

import me.madhead.gradle.ngrok.config.NgrokExtension
import me.madhead.gradle.ngrok.config.Protocol
import me.madhead.gradle.ngrok.service.NgrokService
import me.madhead.gradle.ngrok.tasks.NgrokStartTask
import me.madhead.gradle.ngrok.tasks.NgrokStopTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Provider

class GradleNgrokPlugin : Plugin<Project> {
    internal companion object {
        const val TASK_GROUP = "ngrok"
    }

    /**
     * @inheritDoc
     */
    override fun apply(project: Project) {
        val ngrokExtension = project.ngrokExtension()
        val ngrokService = project.ngrokService(ngrokExtension)

        project.ngrokTasks(ngrokService)
    }

    private fun Project.ngrokExtension(): NgrokExtension {
        val ngrokExtension = extensions.create("ngrok", NgrokExtension::class.java)

        ngrokExtension.port.convention(8080)
        ngrokExtension.protocol.convention(Protocol.HTTP)

        return ngrokExtension
    }

    private fun Project.ngrokService(
        ngrokExtension: NgrokExtension,
    ): Provider<NgrokService> = gradle.sharedServices.registerIfAbsent("ngrok", NgrokService::class.java) { spec ->
        spec.parameters { parameters ->
            parameters.ngrokExtension.set(ngrokExtension)
        }
    }

    private fun Project.ngrokTasks(
        ngrokService: Provider<NgrokService>,
    ) {
        tasks.register("ngrokStart", NgrokStartTask::class.java) { task ->
            task.usesService(ngrokService)
            task.ngrok.set(ngrokService)
        }

        tasks.register("ngrokStop", NgrokStopTask::class.java) { task ->
            task.usesService(ngrokService)
            task.ngrok.set(ngrokService)
        }
    }
}
