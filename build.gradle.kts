plugins {
    kotlin("jvm") version "2.2.20"
    id("java")
    id("com.gradleup.shadow") version "9.3.1"
    id("maven-publish")
}

group = "com.github.devwaffles"
description = "VerneGUI"
version = "1.2.3-beta"

kotlin {
    jvmToolchain(8)
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin")
        }
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:2.2.20")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect:2.2.20")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("com.google.inject:guice:4.1.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = project.description
            version = project.version.toString()
        }
    }
}

tasks {
    java {
        withSourcesJar()
    }
}