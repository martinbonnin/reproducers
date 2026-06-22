package com.example

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

open class ExamplePlugin: Plugin<Project> {
  override fun apply(target: Project) {
    target.tasks.register("bar1", Bar::class.java)
    target.extensions.create("example", ExampleExtension::class.java, target)
  }
}

open class ExampleExtension(private val project: Project) {
  fun registerBar() {
    project.tasks.register("bar2", Bar::class.java)
  }
}


open class Bar: DefaultTask() {
  @TaskAction
  fun taskAction() {
    error("Ouille")
  }
}