plugins {
    id("me.madhead.gradle.ngrok")
    kotlin("jvm").version("1.8.10")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-netty:2.2.3")
}
