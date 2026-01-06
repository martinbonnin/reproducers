plugins {
  id("org.jetbrains.kotlin.jvm").version("2.3.0")
  id("com.apollographql.apollo").version("5.0.0-alpha.3")
}

apollo {
  service("service") {
    packageName.set("test")
  }
}

dependencies {
  implementation("com.apollographql.apollo:apollo-runtime")
  implementation("com.apollographql.mockserver:apollo-mockserver:0.3.1")
  testImplementation(kotlin("test"))
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

tasks.test {
  useJUnitPlatform()
}
