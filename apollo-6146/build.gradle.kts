plugins {
  id("com.android.library").version("8.6.0")
  id("org.jetbrains.kotlin.android").version("2.0.20")
  id("maven-publish")
}


abstract class GenerateSource: DefaultTask() {
  @get:Input
  abstract val username: Property<String>

  @get:OutputDirectory
  abstract val outputDir: DirectoryProperty

  @TaskAction
  fun taskAction() {
    outputDir.get().asFile.resolve("com/example/username.kt").apply {
      parentFile.mkdirs()
      writeText("""
      package com.example

      val username = "$username"
    """.trimIndent())
    }
  }
}

val generateSourceTaskProvider = tasks.register("generateSource", GenerateSource::class.java) {
  username = "World"
  outputDir.set(file("build/generated-source"))
}

project.kotlin.sourceSets.getByName("main").kotlin.srcDir(generateSourceTaskProvider)

android {
  namespace = "com.example"
  defaultConfig {
    minSdk = 24
    compileSdk = 34
  }

  publishing {
    singleVariant("release") {
      withSourcesJar()
    }
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

group = "com.example"
version = "0.0.0"

publishing {
  publications {
    create("lib", MavenPublication::class.java) {

      afterEvaluate {
        from(components.getByName("release"))
      }
    }
  }
}


