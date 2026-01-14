plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  implementation("com.gradleup.nmcp.settings:com.gradleup.nmcp.settings.gradle.plugin:1.4.3")
  implementation("com.gradle:develocity-gradle-plugin:4.3")
}