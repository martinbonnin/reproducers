include(":moduleA")
include(":moduleB")

pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach { it.mavenCentral() }
}