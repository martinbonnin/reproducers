plugins {
  id("com.android.library").version("8.2.2")
  id("org.jetbrains.kotlin.android").version("2.0.20")
  id("com.apollographql.apollo").version("4.0.0")
  id("maven-publish")
}

dependencies {
  implementation("com.apollographql.apollo:apollo-api")
}

apollo {
  service("service") {
    packageName.set("com.example")
  }
}

android {
  namespace = "com.example"
  defaultConfig {
    minSdk = 24
    compileSdk = 34
  }

  publishing {
    singleVariant("release") {
      withSourcesJar()
    }
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}
group = "com.example"
version = "0.0.0"

publishing {
  publications {
    create("lib", MavenPublication::class.java) {

      afterEvaluate {
        from(components.getByName("release"))
      }
    }
  }
}


