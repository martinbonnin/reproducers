plugins {
  id("org.jetbrains.kotlin.jvm").version("2.2.0")
}

dependencies {
  implementation("dev.gradleplugins:gradle-api:8.0")
  testImplementation(kotlin("test"))
}
