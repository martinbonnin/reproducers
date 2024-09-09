
import kotlinx.serialization.serializerOrNull

buildscript {
  dependencies {
    classpath("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")
  }

  repositories {
    mavenCentral()
  }
}

@OptIn(kotlinx. serialization. InternalSerializationApi::class)
println(Float::class.serializerOrNull())
