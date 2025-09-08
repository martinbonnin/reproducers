plugins {
  id("base")
}
val outgoingConfiguration = configurations.consumable("outgoingConfiguration")

//group = "com.example"
//version = "-SNAPSHOT"
println("project version is $version")
artifacts {
  add(outgoingConfiguration.name, file("foo.txt"))
}