import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  id("org.jetbrains.kotlin.multiplatform").version("2.1.20")
}

kotlin {
  jvm()

  sourceSets {
    getByName("commonMain") {
      dependencies {
//        implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
        implementation("io.ktor:ktor-client-core:3.1.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
      }
    }
  }

  compilerOptions {
    apiVersion.set(KotlinVersion.KOTLIN_1_9)
    languageVersion.set(KotlinVersion.KOTLIN_1_9)
  }
  coreLibrariesVersion = "1.9.0"
}

