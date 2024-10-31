@file:Suppress("UnstableApiUsage")

import org.gradle.api.internal.provider.AbstractMinimalProvider
import org.gradle.api.internal.tasks.TaskDependencyResolveContext

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.testsrcdir"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.testsrcdir"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

abstract class GenerateSourceTask : DefaultTask() {
    @get:Input
    abstract val foo: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun taskAction() {
        outputDir.get().asFile.apply {
            deleteRecursively()
            mkdirs()
            resolve("outputFile.kt").writeText(
                """
                package com.example
                
                val someProperty = "${foo.get()}"
            """.trimIndent()
            )
        }
    }
}

val taskProvider = tasks.register("generateSource", GenerateSourceTask::class) {
    foo.set("Foo")
    outputDir.set(layout.buildDirectory.dir("testOutputDir"))
}

/**
 * Set this to false to make the check pass
 */
if (true) {
    kotlin.sourceSets.getByName("main").kotlin.srcDirs(taskProvider)
} else {
    androidComponents {
        onVariants { variant ->
            variant.sources.kotlin!!.addGeneratedSourceDirectory(taskProvider, { it.outputDir })
        }
    }
}


androidComponents {
    onVariants { variant ->
        val mainSourceSets = variant.sources
        val kotlinSources = mainSourceSets.kotlin?.all

        val dependencies = mutableListOf<Any>()
        val context = object : TaskDependencyResolveContext {
            override fun add(dependency: Any) {
                dependencies.add(dependency)
            }
            override fun visitFailure(failure: Throwable) {}
            override fun getTask(): Task? { return null }
        }

        (kotlinSources as AbstractMinimalProvider<*>).visitDependencies(context)

        check(dependencies.size > 0) {
            "No dependencies were found for property $kotlinSources"
        }
        println("dependencies=$dependencies")
    }
}

abstract class DumpSourcesTask : DefaultTask() {
    @get:InputFiles
    abstract val files: ConfigurableFileCollection

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun taskAction() {
        outputFile.get().asFile.writeText(
            files.files.joinToString { it.path }
        )
    }
}


//val androidComponents2 = project.extensions.getByType(AndroidComponentsExtension::class.java)
//androidComponents2.onVariants { variant ->
//
//    println("dependencies are: $dependencies")
//
//    tasks.register("dump${variant.name}Sources", DumpSourcesTask::class) {
//        files.from(kotlinSources)
//        outputFile.set(layout.buildDirectory.file("sources.dump"))
//    }
//}