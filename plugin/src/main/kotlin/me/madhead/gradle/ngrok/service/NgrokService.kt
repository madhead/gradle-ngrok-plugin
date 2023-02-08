package me.madhead.gradle.ngrok.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import me.madhead.gradle.ngrok.api.NgrokClient
import me.madhead.gradle.ngrok.config.NgrokExtension
import me.madhead.gradle.ngrok.config.Protocol
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.slf4j.LoggerFactory
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

abstract class NgrokService : BuildService<NgrokService.Parameters>, AutoCloseable {
    private val logger = LoggerFactory.getLogger(NgrokService::class.java)
    private var ngrok: Process? = null
    private val ngrokClient = NgrokClient()
    private lateinit var publicUrl: String

    /**
     * Starts ngrok tunnel.
     */
    fun start() {
        val extension = this.parameters.ngrokExtension.get()
        val command = buildList {
            add("ngrok")
            when (extension.protocol.get()) {
                Protocol.HTTP -> add("http")
                Protocol.TCP -> add("tcp")
            }
            add(extension.port.get().toString())
        }

        ProcessBuilder(command)
            .inheritIO()
            .start()
            .also {
                logger.info("Started ngrok({}): {}", it.pid(), it.info().commandLine().orElse(""))
                ngrok = it
            }


        publicUrl = runBlocking {
            withTimeout(1.seconds) {
                var result: String? = null

                while (result == null) {
                    result = ngrokClient.tunnels().tunnels.firstOrNull()?.publicUrl
                    delay(50.milliseconds)
                }
                result
            }
        }
    }

    val url: String
        get() = this.publicUrl

    /**
     * Stops ngrok tunnel.
     */
    fun stop() {
        ngrok?.let {
            logger.info("Stopping ngrok({})", it.pid())
            it.destroy()
        }
        ngrokClient.close()
    }

    /**
     * Stops ngrok tunnel. This method is called by Gradle when a build finishes. It's actually a wrapper around [stop].
     */
    override fun close() {
        stop()
    }

    interface Parameters : BuildServiceParameters {
        val ngrokExtension: Property<NgrokExtension>
    }
}
