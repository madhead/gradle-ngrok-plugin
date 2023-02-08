package me.madhead.gradle.ngrok.tasks

import me.madhead.gradle.ngrok.GradleNgrokPlugin
import me.madhead.gradle.ngrok.service.NgrokService
import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

/**
 * Stops ngrok tunnel.
 */
abstract class NgrokStopTask @Inject constructor(
    objects: ObjectFactory,
) : DefaultTask() {
    /**
     * Stops ngrok tunnel.
     */
    @TaskAction
    fun stop() {
        ngrok.get().stop()
    }

    /**
     * @inheritDoc
     */
    override fun getGroup(): String = GradleNgrokPlugin.TASK_GROUP

    /**
     * @inheritDoc
     */
    override fun getDescription(): String = "Stops ngrok."

    @get:Internal
    internal val ngrok = objects.property(NgrokService::class.java)
}
