plugins {
  java
//  id("com.gradleup.tapmoc") version "0.4.2"
}

// groovy-all 3.x is a POM-only artifact (no JAR published, only a BOM POM).
// The @pom suffix forces Gradle to resolve it as a POM-type artifact, reproducing
// how dev.gradleplugins:gradle-test-kit:8.11.1 declares it in its Maven POM:
//
//   <dependency>
//     <groupId>org.codehaus.groovy</groupId>
//     <artifactId>groovy-all</artifactId>
//     <version>3.0.22</version>
//     <type>pom</type>         <-- this
//     <scope>runtime</scope>
//   </dependency>
//
// Without @pom, Gradle treats the POM packaging as a BOM import and does not add
// groovy-all itself to the dependency graph. With @pom, Gradle creates an adhoc
// component with a single variant: artifactType=pom.
dependencies {
  runtimeOnly("org.codehaus.groovy:groovy-all:3.0.22@pom")
}

//tapmoc {
//  java(11)
//  // checkDependencies() enables tapmocCheckClassFileVersions.
//  // With it: fails at task execution (both with and without --no-configuration-cache).
//  // Without it: task is disabled (SKIPPED), but still fails at configuration cache
//  //             serialization because Gradle eagerly resolves task inputs.
//  checkDependencies()
//}

configurations.getByName("runtimeClasspath").attributes.apply {
  for (key in keySet()) {
    println("$key -> ${getAttribute(key)}")
  }
}