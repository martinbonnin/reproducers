import org.gradle.declarative.dsl.schema.FqName.Empty.packageName

plugins {
  id("org.jetbrains.kotlin.jvm").version("2.1.20")
  id("com.apollographql.apollo").version("4.2.0")
}


apollo {
  service("service") {
    packageName.set("com.example")

  }
}