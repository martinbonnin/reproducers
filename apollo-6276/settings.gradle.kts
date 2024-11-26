pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.mavenCentral()
    it.mavenLocal()
  }
}


includeBuild("../../apollo-kotlin-execution")
