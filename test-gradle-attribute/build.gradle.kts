val configuration = configurations.create("test")
configuration.dependencies.add(dependencies.create("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21"))

println("before resolution")
println(configuration.files.filter { it.name.contains("kotlin-gradle-plugin-2.0.21") })