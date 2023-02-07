plugins {
    `java-gradle-plugin`
    `embedded-kotlin`
}

repositories {
    mavenCentral()
}

dependencies {
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest()
        }

        val functionalTest by registering(JvmTestSuite::class) {
            useKotlinTest()

            dependencies {
                implementation(project())
            }

            targets {
                all {
                    testTask.configure { shouldRunAfter(test) }
                }
            }
        }
    }
}

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

    val functionalTest by sourceSets

    testSourceSets(functionalTest)
}

tasks {
    named("check") {
        val functionalTest by testing.suites

        dependsOn(functionalTest)
    }
}
