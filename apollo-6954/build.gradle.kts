plugins {
  id("org.jetbrains.kotlin.jvm").version("2.4.0-RC")
  id("com.apollographql.apollo").version("5.0.0")
}

apollo {
  service("service") {
    packageName.set("com.example.apollo.service")
    generateDataBuilders = true
  }
}

dependencies {
  implementation(apollo.deps.runtime)
}