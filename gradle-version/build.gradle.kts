
val config = configurations.create("config")
dependencies {
  add(config.name, project(":lib"))
}