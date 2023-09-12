plugins {
    kotlin("jvm") version "1.9.0"
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("maven-publish")
}

group = "com.github.devwaffles"
description = "VerneGUI"
version = "1.0.2-alpha"

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
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    compileOnly("com.destroystokyo.paper:paper-api:1.9.4-R0.1-SNAPSHOT")
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