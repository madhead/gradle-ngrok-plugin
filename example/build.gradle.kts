import me.madhead.gradle.ngrok.config.Protocol.HTTP
import me.madhead.gradle.ngrok.service.NgrokService

plugins {
    kotlin("jvm").version("1.8.10")
    application
    id("me.madhead.gradle.ngrok")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-netty:2.2.3")
}

application {
    mainClass.set("me.madhead.gradle.ngrok.example.MainKt")
}

ngrok {
    port.set(8080)
    protocol.set(HTTP)
}

tasks {
    named<JavaExec>("run") {
        val ngrokServiceProvider = project.gradle.sharedServices.registrations["ngrok"].service

        usesService(ngrokServiceProvider)

        doFirst {
            (ngrokServiceProvider.get() as NgrokService).start()
        }

        argumentProviders.add(CommandLineArgumentProvider { listOf((ngrokServiceProvider.get() as NgrokService).url) })

        doLast {
            (ngrokServiceProvider.get() as NgrokService).stop()
        }
    }
}
