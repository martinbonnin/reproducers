import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.jetbrains.kotlin.multiplatform").version("2.0.21")
}

kotlin {
  coreLibrariesVersion = "2.0.0"
  macosArm64()
  iosArm64()

  sourceSets {
    getByName("commonTest") {
      dependencies {
        implementation(kotlin("test"))
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
        implementation("io.ktor:ktor-client-core:3.0.0")
      }
    }
  }
}

tasks.withType<KotlinCompile>().configureEach {
  this.compilerOptions.apiVersion.set(KotlinVersion.KOTLIN_2_0)
}