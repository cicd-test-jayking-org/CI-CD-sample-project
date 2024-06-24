import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.cloud.tools.jib") version "3.4.3"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.github.microutils:kotlin-logging:1.12.5")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jib {
    val env = System.getenv("DEPLOY_ENVIRONMENT")
    from {
        image = "amazoncorretto:21"
    }
    to {
        image =
            "${System.getenv("AWS_ECR_REGISTRY")}/${System.getenv("AWS_ECR_REPOSITORY_NAME")}:${System.getenv("IMAGE_TAG")}"
    }
    container {
        jvmFlags = when (env) {
            "alpha" -> mutableListOf(
                "-Xms512m",
                "-Xmx1024m",
                "-Dspring.profiles.active=$env",
                "-Duser.timezone=UTC",
                "-XX:+UseContainerSupport",
            )

            "staging" -> mutableListOf(
                "-Xms512m",
                "-Xmx1024m",
                "-Dspring.profiles.active=$env",
                "-Duser.timezone=UTC",
                "-XX:+UseContainerSupport",
            )

            else -> emptyList()
        }
        creationTime = "USE_CURRENT_TIMESTAMP"
        ports = mutableListOf("8080")
        mainClass = "org.example.Main"
    }
}

