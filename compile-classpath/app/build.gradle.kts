plugins {
  id("com.android.library").version("8.0.0")
  id("org.jetbrains.kotlin.android").version("1.8.0")
}

dependencies {
  implementation(project(":lib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
}

//tasks.compileJava {
//  options.release.set(8)
//}

android {
  namespace = "com.example"
  compileSdk = 33
  defaultConfig {
    minSdk = 21
  }
}