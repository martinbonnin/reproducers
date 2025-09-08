import com.apollographql.apollo.compiler.PackageNameGenerator

plugins {
  id("org.jetbrains.kotlin.jvm").version("2.2.0")
  id("com.apollographql.apollo").version("4.3.3")
}

apollo {
  service("service") {
    packageNameGenerator.set(object : PackageNameGenerator {
      override fun packageName(filePath: String): String {
        println("filePath is $filePath")
        return when {
          filePath.contains("fragment") -> "foo"
          else -> "bar"
        }
      }

      override val version: String
        get() = "3"
    })
  }
}
