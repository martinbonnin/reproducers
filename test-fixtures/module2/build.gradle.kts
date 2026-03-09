@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
}

android {
    namespace = "com.feature2"
    compileSdk = 36

    testFixtures {
        enable = true
    }
}

dependencies {
    testImplementation(testFixtures(project(":module1")))
}