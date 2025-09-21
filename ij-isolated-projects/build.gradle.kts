plugins {
  id("org.jetbrains.kotlin.jvm").version("2.2.20")
  id("com.apollographql.apollo").version("4.3.3")
}

apollo {
  service("service") {
    packageName.set("com.example")
  }
}