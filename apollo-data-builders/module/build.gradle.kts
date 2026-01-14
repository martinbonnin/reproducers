plugins {
  id("org.jetbrains.kotlin.jvm")
  id("com.apollographql.apollo")
}

apollo {
  service("service") {
    packageName.set("com.example")
    generateApolloMetadata.set(true)
    generateDataBuilders.set(true)
    alwaysGenerateTypesMatching.set(emptyList())
  }
}

dependencies {
  add("apolloService", project(":schema"))
}