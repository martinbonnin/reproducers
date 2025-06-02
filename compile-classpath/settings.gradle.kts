pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.mavenCentral()
    it.google()
  }
}


include(":lib", ":app")