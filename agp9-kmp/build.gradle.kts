import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.kotlin.multiplatform.library").version("9.0.0-alpha09")
  id("org.jetbrains.kotlin.multiplatform").version("2.2.20")
}

kotlin {
  jvm {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_23)
    }
  }
  android {
    namespace = "com.example"
    compileSdk = 36
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_23)
    }
  }

  compilerOptions {
    freeCompilerArgs.add("-Xjdk-release=23")
    freeCompilerArgs.add("-Xjvm-enable-preview")
  }
}
