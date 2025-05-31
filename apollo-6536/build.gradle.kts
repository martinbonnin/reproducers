plugins {
  id("org.jetbrains.kotlin.jvm").version("2.1.20")
  id("com.apollographql.apollo").version("4.1.1")
}

apollo {
  service("service") {
    packageName.set("com.example")
    mapScalar("UUID", "kotlin.String")
  }
}