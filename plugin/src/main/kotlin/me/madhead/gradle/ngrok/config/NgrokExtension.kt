package me.madhead.gradle.ngrok.config

import org.gradle.api.provider.Property

/**
 * Container for plugin configuration.
 */
interface NgrokExtension {
    /**
     * Local port to be exposed via a tunnel.
     */
    val port: Property<Int>

    /**
     * Tunnel protocol.
     */
    val protocol: Property<Protocol>
}
