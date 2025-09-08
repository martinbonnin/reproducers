pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.maven("https://redirector.kotlinlang.org/maven/dev/")
    it.mavenCentral()
  }
}