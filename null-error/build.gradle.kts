plugins {
  id("org.jetbrains.kotlin.jvm").version("2.2.0")
  id("com.apollographql.apollo").version("4.3.2")
}


dependencies {
  implementation("com.apollographql.apollo:apollo-runtime")

  testImplementation(kotlin("test"))
}

apollo {
  service("service") {
    packageName.set("com.example")
  }
}
