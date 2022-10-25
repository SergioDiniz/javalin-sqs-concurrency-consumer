import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:5.1.2")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("io.insert-koin:koin-core:3.2.2")

    // Others
    implementation("com.fasterxml:classmate:1.5.1")

    // AWS SQS
    implementation("com.amazonaws:amazon-sqs-java-messaging-lib:1.0.8")
    implementation("com.amazonaws:aws-java-sdk-sqs:1.12.322")
    implementation("com.amazonaws:aws-java-sdk-sts:1.12.322")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}