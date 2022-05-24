plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}


application {
    // Define the main class for the application.
    mainClass.set("euler.AppKt")
}
