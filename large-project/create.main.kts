#!/usr/bin/env kotlin

import java.io.File

val root = File(args[0])

root.mkdirs()
root.resolve("build.gradle.kts").writeText("""
  plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0" apply(false)
    id("com.apollographql.apollo") version "4.1.1-SNAPSHOT" apply(false)
  }

  println("Process: ${ProcessHandle.current().pid()}")

""".trimIndent())


root.resolve("settings.gradle.kts").writeText("""
pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.mavenCentral()
    it.mavenLocal()
  }
}
""".trimIndent())

repeat(100) {
  root.resolve("module$it").let { dir ->
    dir.mkdirs()
    dir.resolve("build.gradle.kts").writeText("""
        plugins {
          id("org.jetbrains.kotlin.jvm")
          id("com.apollographql.apollo")
        }
        
        apollo {
          service("service") {
            packageName.set("com.module$it")
            dependsOn(project(":schema"), true)
          }
        }
        dependencies {
          implementation("com.apollographql.apollo:apollo-runtime")
        }
    """.trimIndent())

    dir.resolve("src/main/graphql").mkdirs()
    dir.resolve("src/main/graphql/operation$it.graphql").writeText("query GetFoo$it { foo$it: foo }")
  }

  root.resolve("settings.gradle.kts").appendText("include(\":module$it\")\n")
}

val dir = root.resolve("schema")
dir.mkdirs()

dir.resolve("build.gradle.kts").writeText("""
        plugins {
          id("org.jetbrains.kotlin.jvm")
          id("com.apollographql.apollo")
        }
        
        apollo {
          service("service") {
            packageName.set("com.schema")
            generateApolloMetadata.set(true)
          }
        }
        dependencies {
          implementation("com.apollographql.apollo:apollo-runtime")
        }
    """.trimIndent())

dir.resolve("src/main/graphql").mkdirs()
dir.resolve("src/main/graphql/schema.graphqls").writeText("type Query { foo: Int }")

root.resolve("settings.gradle.kts").appendText("include(\":schema\")\n")

