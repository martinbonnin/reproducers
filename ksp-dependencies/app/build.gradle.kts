plugins {
  id("org.jetbrains.kotlin.jvm")
  id("com.google.devtools.ksp")
}

dependencies {
  implementation(project(":lib"))
  ksp(project(":processor"))
}