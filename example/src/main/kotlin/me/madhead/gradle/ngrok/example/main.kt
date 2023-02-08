package me.madhead.gradle.ngrok.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    embeddedServer(factory = Netty, port = 8080) {
        install(ShutDownUrl.ApplicationCallPlugin) {
            shutDownUrl = "/shutdown"
            exitCodeSupplier = { 0 }
        }
        routing {
            get("/") {
                call.respondText(
                    """
                        Hello, world!

                        My URL: ${args[0]}
                    """.trimIndent()
                )
            }
        }
    }.start(wait = true)
}
