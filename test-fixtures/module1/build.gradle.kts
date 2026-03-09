@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
}

android {
    namespace = "com.feature1"
    compileSdk = 36

    testFixtures {
        enable = true
    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("junit:junit:4.13.2")
}


abstract class GenerateTestFixtures: DefaultTask() {
    @get:Input
    abstract val foo: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun taskAction() {
        outputDir.get().asFile.apply {
            deleteRecursively()
            mkdirs()
            resolve("generatedTestFixtures.kt").writeText("""
                val generatedTestFixture = "hello"
            """.trimIndent())
        }
    }
}

val generateTestFixturesTaskProvider = tasks.register("generateTestFixtures", GenerateTestFixtures::class.java) {
    foo.set("foo")
    outputDir.set(layout.buildDirectory.dir("generated-test-fixtures"))
}

androidComponents {
    onVariants {
        it.testFixtures!!.sources.kotlin!!.addGeneratedSourceDirectory(generateTestFixturesTaskProvider) { it.outputDir }
    }
}