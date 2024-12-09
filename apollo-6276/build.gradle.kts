plugins {
  id("com.google.devtools.ksp").version("2.0.21-1.0.28")
  id("org.jetbrains.kotlin.jvm").version("2.0.21")
  id("com.apollographql.apollo").version("4.1.0")
  id("com.apollographql.execution").version("0.1.0")
}

dependencies {
  implementation("com.apollographql.execution:apollo-execution-runtime")
  implementation("com.apollographql.execution:apollo-execution-ktor")
  implementation("com.apollographql.apollo:apollo-runtime")
  implementation("com.apollographql.apollo:apollo-normalized-cache")
  implementation("io.ktor:ktor-server-netty:3.0.0")
  testImplementation(kotlin("test"))
}

// Configure codegen
apolloExecution {
  service("service") {
    packageName = "com.example.server"
  }
}

apollo {
  service("service") {
    packageName.set("com.example.client")
    srcDir("graphql")
  }
}