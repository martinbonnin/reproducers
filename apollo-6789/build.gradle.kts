plugins {
  id("org.jetbrains.kotlin.jvm").version("2.3.0")
  id("com.apollographql.apollo").version("5.0.0-alpha.3")
}

dependencies {
  testImplementation(kotlin("test"))
  implementation(apollo.deps.runtime)
}

apollo {
  service("service") {
    packageName.set("foo")
  }
}