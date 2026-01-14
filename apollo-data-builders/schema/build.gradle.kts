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
    isADependencyOf(project(":module"))
  }
}

//dependencies {
//  add("apolloServiceUsedCoordinates", project(":module"))
//}