plugins {
  id("org.jetbrains.kotlin.jvm").version("2.0.0")
  id("com.apollographql.apollo.external").version("4.0.0")
}

apollo {
  service("service") {
    packageName.set("com.example")
    introspection {
      this.endpointUrl.set("https://staging-graphql.outerspatial.com/v1/graphql")
      this.schemaFile.set(file("schema.graphqls"))
    }
  }
}