plugins {
  id("org.jetbrains.kotlin.jvm").version("1.8.0")
}

dependencies {
  implementation(project(":lib"))
}

tasks.compileKotlin {
  compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
}

tasks.compileJava {
  options.release.set(8)
}