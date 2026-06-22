plugins {
  id("java")
  id("com.gradleup.tapmoc").version("0.4.2")
  id("com.apollographql.apollo").version("5.0.0")
}

tapmoc {
  java(17)
}

dependencies {
  implementation("com.squareup.okhttp3:mockwebserver3:5.3.0")
  implementation("com.apollographql.java:client:0.0.3-SNAPSHOT")
  testImplementation("junit:junit:4.13.1")
}

apollo {
  service("service") {
    packageName.set("com.example")
    generateKotlinModels.set(false)
  }
}