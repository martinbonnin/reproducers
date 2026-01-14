pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.mavenCentral()
    it.maven("https://central.sonatype.com/repository/maven-snapshots/")
  }
}


include(":module", ":schema")