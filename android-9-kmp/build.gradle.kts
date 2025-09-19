plugins {
  id("org.jetbrains.kotlin.multiplatform").version("2.2.20")
  id("com.android.kotlin.multiplatform.library").version("9.0.0-alpha05")
}

kotlin {
  jvm()
  android {
    compileSdk = 36
    namespace = "com.example"
  }
}

androidComponents {
  onVariants(selector().all()) {
    it.sources.kotlin!!.addStaticSourceDirectory("static")
  }
}
