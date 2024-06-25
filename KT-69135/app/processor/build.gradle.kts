plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation("com.google.devtools.ksp:symbol-processing-api:2.0.0-1.0.21")
            }
        }
    }
}



