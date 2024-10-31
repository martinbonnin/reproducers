plugins {
  id("org.jetbrains.kotlin.jvm").version("2.0.21")
}

dependencies {
  testImplementation("io.ktor:ktor-network:3.0.0")
  testImplementation("io.ktor:ktor-client-core:3.0.0")
  testImplementation("io.ktor:ktor-client-okhttp:3.0.0")
  testImplementation(kotlin("test"))
}