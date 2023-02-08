package me.madhead.gradle.ngrok.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [Tunnel details](https://ngrok.com/docs/ngrok-agent/api#tunnel-detail).
 */
@Serializable
data class Tunnel(
    @SerialName("public_url")
    val publicUrl: String,
)

/**
 * [Tunnels list](https://ngrok.com/docs/ngrok-agent/api#list-tunnels).
 */
@Serializable
data class TunnelList(
    val tunnels: List<Tunnel>,
)
