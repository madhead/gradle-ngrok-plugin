package me.madhead.gradle.ngrok.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * [ngrok Agent API](https://ngrok.com/docs/ngrok-agent/api) client.
 */
class NgrokClient : AutoCloseable {
    private val client = HttpClient(Java) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    /**
     * Get the list of active tunnels.
     */
    suspend fun tunnels(): TunnelList {
        return client.get("http://localhost:4040/api/tunnels").body()
    }

    /**
     * Close this client and free its resources.
     */
    override fun close() {
        client.close()
    }
}
