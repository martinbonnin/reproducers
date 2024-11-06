
dependencyLocking {
  lockAllConfigurations()
  lockFile.set(file("${project.projectDir}/gradle.lockfile"))
}

repositories {
  mavenCentral()
}

val dependencyScope = configurations.dependencyScope("dependencyScope").get()
dependencyScope.dependencies.add(dependencies.create("com.apollographql.apollo:apollo-compiler:4.1.0"))
dependencyScope.dependencies.add(dependencies.platform("org.jetbrains.kotlinx:kotlinx-serialization-bom:1.6.2"))

val resolvable = configurations.resolvable("resolvable").get()
resolvable.extendsFrom(dependencyScope)

resolvable.resolve()
