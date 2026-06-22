@file:OptIn(ExperimentalAbiValidation::class)

import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
  id("org.jetbrains.kotlin.jvm").version("2.4.0-Beta2")
}

kotlin {
  abiValidation {

  }
}
