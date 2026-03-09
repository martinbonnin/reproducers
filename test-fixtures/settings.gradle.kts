pluginManagement {
    listOf(repositories, dependencyResolutionManagement.repositories).forEach {
        it.mavenCentral()
        it.google()
    }
}

include(":module1", ":module2")