plugins {
  id("org.jetbrains.kotlin.jvm").version("2.0.0")
  id("com.apollographql.apollo").version("4.0.0")
}

apollo {
  service("service") {
    packageName.set("com.example")
    generateApolloMetadata.set(true)
    isADependencyOf(project(":feature"))
  }
}


