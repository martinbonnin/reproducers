plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.google.devtools.ksp")
    id("com.android.library")
}

dependencies {
    add("kspCommonMainMetadata", project(":processor"))
}

android {
    compileSdk = 30
    namespace = "com.example.myapp"
}
kotlin {
    androidTarget()
    jvm()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation("com.example:annotations:0.0.1")
            }
        }
        getByName("jvmMain") {
            dependencies {
                implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:2.0.0-1.0.21")
            }
        }
    }
}



