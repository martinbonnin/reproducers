include(":module1", ":module2")

pluginManagement {
  listOf(dependencyResolutionManagement.repositories, repositories).forEach {
    it.mavenCentral()
  }
}
