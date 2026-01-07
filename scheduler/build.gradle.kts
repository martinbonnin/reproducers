plugins {
  id("org.jetbrains.kotlin.jvm").version("2.3.0")
  id("application")
  id("com.apollographql.apollo").version("5.0.0-alpha.3")
  id("org.jetbrains.kotlinx.atomicfu").version("0.29.0")
}

apollo {
  service("service") {
    packageName.set("test")
  }
}

application {
  this.mainClass.set("ServerKt")
}

dependencies {
  implementation("com.apollographql.apollo:apollo-runtime")
  implementation("com.apollographql.apollo:apollo-normalized-cache")
  implementation("org.jetbrains.kotlinx:atomicfu:0.29.0")
  implementation("io.ktor:ktor-server-core:3.3.3")
  implementation("io.ktor:ktor-server-cio:3.3.3")
  testImplementation("io.ktor:ktor-client-core:3.3.3")
  testImplementation("io.ktor:ktor-client-cio:3.3.3")
  testImplementation(kotlin("test"))
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

tasks.test {
  useJUnitPlatform()
//  systemProperty("kotlinx.coroutines.scheduler.keep.alive.sec", "1")
//  systemProperty("kotlinx.coroutines.io.parallelism", "1")
}

