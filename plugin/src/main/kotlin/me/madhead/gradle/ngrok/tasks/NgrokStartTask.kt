package me.madhead.gradle.ngrok.tasks

import me.madhead.gradle.ngrok.GradleNgrokPlugin
import me.madhead.gradle.ngrok.service.NgrokService
import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

/**
 * Starts ngrok tunnel.
 */
abstract class NgrokStartTask @Inject constructor(
    objects: ObjectFactory,
) : DefaultTask() {
    /**
     * Starts ngrok tunnel.
     */
    @TaskAction
    fun start() {
        ngrok.get().start()
    }

    /**
     * @inheritDoc
     */
    override fun getGroup(): String = GradleNgrokPlugin.TASK_GROUP

    /**
     * @inheritDoc
     */
    override fun getDescription(): String = "Starts ngrok."

    @get:Internal
    internal val ngrok = objects.property(NgrokService::class.java)
}
