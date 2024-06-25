plugins {
    id("org.jetbrains.kotlin.multiplatform").version("2.0.0")
    id("maven-publish")
}

group = "com.example"
version = "0.0.1"

kotlin {
    jvm()
    // Uncomment to fix the issue
    //macosArm64()
}