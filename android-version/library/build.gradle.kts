plugins {
    id("com.android.library")
    id("com.apollographql.apollo").version("5.0.0-alpha.4")
}

afterEvaluate {
    apollo {
        service("foo") {
            packageName.set("com.example")
        }
    }
}

android {
    compileSdk = 35
    namespace = "com.example"
}