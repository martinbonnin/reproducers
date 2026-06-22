import org.gradle.declarative.dsl.schema.FqName.Empty.packageName

plugins {
  id("org.jetbrains.kotlin.jvm").version("2.4.0")
  id("com.apollographql.apollo").version("5.0.0")
}


apollo {
  service("service") {
    packageName.set("com.example")
  }
}

dependencies {
  implementation(apollo.deps.runtime)
  testImplementation(kotlin("test"))
}
