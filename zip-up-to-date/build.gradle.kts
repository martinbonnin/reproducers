tasks.register("zip", Zip::class.java) {
  from(file("inputs"))

  exclude("2")

  archiveBaseName.set("myzip")
  destinationDirectory.set(layout.buildDirectory)
}