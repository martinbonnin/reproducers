plugins {
  id("com.google.devtools.ksp").version("2.0.21-1.0.28")
  id("org.jetbrains.kotlin.jvm").version("2.0.21")
  id("com.apollographql.apollo3").version("3.8.6-SNAPSHOT")
  id("com.apollographql.execution").version("0.1.0")
}

apollo {

}

dependencies {
  implementation("com.apollographql.execution:apollo-execution-runtime")
  implementation("com.apollographql.execution:apollo-execution-ktor")
  implementation("com.apollographql.apollo3:apollo-runtime")
  implementation("com.apollographql.apollo3:apollo-normalized-cache")
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
    //schemaFiles.from("graphql/schema.graphqls")
  }
}