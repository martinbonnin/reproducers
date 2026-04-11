import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  id("org.jetbrains.kotlin.jvm").version("2.3.0")
}

kotlin {
  this.compilerOptions {
//    languageVersion.set()
    @Suppress("DEPRECATION")
    languageVersion.set(KotlinVersion.KOTLIN_1_9)
  }
}