import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest

plugins {
  id("org.jetbrains.kotlin.multiplatform").version("2.2.0")
}


kotlin {
  wasmJs {
    browser {
      testTask {
        useKarma {
          useChromeHeadless()
        }
      }
    }
  }
  sourceSets.getByName("wasmJsMain").dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${getKotlinPluginVersion()}")
  }
  sourceSets.getByName("wasmJsTest").dependencies {
    // Forgot to explicitely upgrate the version here
    implementation(kotlin("test"))
  }
  coreLibrariesVersion = "2.0.0"
}

