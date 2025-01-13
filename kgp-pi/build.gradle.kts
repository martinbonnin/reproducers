plugins {
  id("org.jetbrains.kotlin.jvm").version("2.1.0")
  id("com.apollographql.apollo").version("4.1.0")
}

dependencies {
  testImplementation(gradleTestKit())
  testImplementation(kotlin("test"))
}

tasks.withType(Test::class.java) {
  inputs.files(fileTree("testProject")).withPathSensitivity(PathSensitivity.RELATIVE).withPropertyName("testProjectFiles")
}