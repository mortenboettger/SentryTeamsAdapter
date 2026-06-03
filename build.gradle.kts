plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "2.3.21"
    id("com.google.devtools.ksp") version "2.3.9"
    id("com.gradleup.shadow") version "9.4.2"
    id("io.micronaut.application") version "5.0.0"
    id("io.micronaut.aot") version "5.0.0"
}

version = "0.0.1"
group = "io.mboettger"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        mavenContent { snapshotsOnly() }
    }
    mavenCentral()
}

dependencies {
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.ktor:ktor-serialization-jackson-jvm:3.2.4")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.2.4")
    implementation("io.ktor:ktor-server-netty-jvm:3.2.4")
    implementation("io.ktor:ktor-client-core:3.2.4")
    implementation("io.ktor:ktor-client-cio:3.2.4")
    implementation("io.ktor:ktor-client-content-negotiation:3.2.4")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.kotlin:micronaut-ktor")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass.set("io.mboettger.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("25")
    targetCompatibility = JavaVersion.toVersion("25")
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
        }
    }
    compileTestKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
        }
    }

    dockerfile {
        baseImage("eclipse-temurin:25")
    }
    dockerBuild {
        images.set(listOf("ghcr.io/mortenboettger/sentry-teams-adapter:snapshot"))
    }
    test {
        failOnNoDiscoveredTests.set(false)
    }
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("io.mboettger.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}



