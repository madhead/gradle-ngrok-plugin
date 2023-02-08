plugins {
    id("com.gradle.plugin-publish").version("1.1.0")
    `embedded-kotlin`
    kotlin("plugin.serialization").version("1.7.10")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlinx:kotlinx-serialization-bom:1.4.1"))
    implementation(platform("io.ktor:ktor-bom:2.2.3"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("io.ktor:ktor-client-java")
    implementation("io.ktor:ktor-client-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-client-encoding:2.2.3")
}

group = "me.madhead.gradle.ngrok"
version = "1.0"

gradlePlugin {
    website.set("https://github.com/madhead/gradle-ngrok-plugin")
    vcsUrl.set("https://github.com/madhead/gradle-ngrok-plugin.git")

    plugins {
        register("ngrok") {
            id = "me.madhead.gradle.ngrok"
            displayName = "Gradle ngrok plugin"
            description = "This plugin allows you to start ngrok tunnel as a part of your build"
            tags.set(listOf("ngrok", "tunnel", "networking"))
            implementationClass = "me.madhead.gradle.ngrok.GradleNgrokPlugin"
        }
    }
}
