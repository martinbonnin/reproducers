plugins {
  id("org.jetbrains.kotlin.jvm").version("2.2.0")
  id("com.apollographql.apollo3").version("3.8.6")
}

apollo {
  service("service") {
    packageName.set("example.com")
  }
  linkSqlite.set(true)
}

dependencies {
  implementation("com.apollographql.apollo3:apollo-runtime")
  //implementation("com.apollographql.apollo3:apollo-normalized-cache")
  implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite")
  implementation("com.apollographql.apollo3:apollo-mockserver")

  testImplementation(kotlin("test"))
}
