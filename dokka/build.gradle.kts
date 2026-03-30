plugins {
  id("org.jetbrains.kotlin.jvm").version("2.3.0").apply(false)
  id("com.android.application").version("8.9.0").apply(false)
  id("org.jetbrains.dokka").version("2.2.0")
}


dokka {
  // Sets properties for the whole project
  dokkaPublications.html {
    moduleName.set("My Project")
  }
}

// Aggregates subproject documentation
dependencies {
  dokka(project(":module1"))
//  dokka(project(":module2"))
  dokka(project(":module3"))
}
