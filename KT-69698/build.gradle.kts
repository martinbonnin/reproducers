plugins {
  // Change to 1.9.0 to make the build fail
  id("org.jetbrains.kotlin.jvm").version("2.0.0")
}

kotlin {
  this.compilerOptions {
    allWarningsAsErrors.set(true)
  }
}