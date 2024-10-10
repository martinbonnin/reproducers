pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach { t -> t.mavenCentral() }
}