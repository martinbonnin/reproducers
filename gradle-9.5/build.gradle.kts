import com.example.ExampleExtension
import com.example.ExamplePlugin

buildscript {
  dependencies {
    classpath("com.example:build-logic")
  }
}

open class Foo: DefaultTask() {
  @TaskAction
  fun taskAction() {
    error("Ouille")
  }
}

tasks.register("foo", Foo::class)

plugins.apply(ExamplePlugin::class.java)

extensions.getByType(ExampleExtension::class.java).registerBar()