plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.6.1"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm")
    testImplementation("io.kotest:kotest-runner-junit5:5.3.0")
    testImplementation("io.kotest:kotest-framework-datatest:5.3.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass.set("euler.AppKt")
}
