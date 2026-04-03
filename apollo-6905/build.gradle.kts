plugins {
  id("org.jetbrains.kotlin.jvm").version("2.3.0")
  id("com.apollographql.apollo").version("5.0.0-alpha.5")
}

apollo {
  service("service") {
    generateDataBuilders.set(true)
    packageName.set("com.example")
  }
}

dependencies {
  implementation("com.apollographql.apollo:apollo-runtime")
  implementation("com.apollographql.apollo:apollo-normalized-cache")

  testImplementation(kotlin("test"))
  testImplementation("com.apollographql.apollo:apollo-testing-support")
}
