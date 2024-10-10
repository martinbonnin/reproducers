import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.jvm.java

buildscript {
  dependencies {
    classpath("com.squareup.okhttp3:okhttp:4.12.0")
  }
}
plugins {
  id("org.jetbrains.kotlin.jvm").version("2.0.0")
}

val embeddedJarFile = file("build/r8/embedded.jar")
val relocatedJarFile = file("build/r8/relocated.jar")
val mappingFile = file("build/r8/mapping.txt")
val lastestR8SHA = "5d3342e25345adcaaedf86e4b13c2d1bbaf0f3e6"
val r8File = file("build/r8/$lastestR8SHA.jar")
val rulesFile = file("rules.pro")

val embeddedJarTaskProvider = tasks.register("embedJar", Zip::class) {
  /**
   * TODO: configuration cache
   */
  from(configurations.getByName("runtimeClasspath").elements.map { fileSystemLocations ->
    files().apply {
      fileSystemLocations.forEach {
        from(zipTree(it.asFile))
      }
    }
  }) {
    exclude("module-info.class")
    exclude("META-INF/versions/*/module-info.class")
  }

  // The jar is mostly empty but this is needed for the plugin descriptor + module-info
  from(tasks.jar.map { zipTree(it.outputs.files.singleFile) })

  /*
   * Exclude libraries R8 rules, we'll add them ourselves
   */
  exclude(
    "META-INF/MANIFEST.MF",
    "META-INF/**/*.pro"
  )

  duplicatesStrategy = DuplicatesStrategy.WARN

  destinationDirectory.set(embeddedJarFile.parentFile)
  archiveFileName.set(embeddedJarFile.name)
}

val downloadR8TaskProvider = tasks.register("downloadR8", DownloadR8Task::class.java) {
  sha1 = lastestR8SHA
  outputFile.set(r8File)
}

val relocatedJarTaskProvider = tasks.register("relocateJar", JavaExec::class) {
  debug = true

  val javaHome = javaToolchainService().compilerFor {
    languageVersion.set(JavaLanguageVersion.of(8))
  }.get().metadata.installationPath.asFile.absolutePath

  dependsOn(embeddedJarTaskProvider)
  classpath(downloadR8TaskProvider)

  inputs.file(rulesFile)
  mainClass.set("com.android.tools.r8.R8")


  args("--release")
  args("--classfile")
  args("--output")
  args(relocatedJarFile.absolutePath)
  args("--pg-map-output")
  args(mappingFile.absolutePath)
  args("--pg-conf")
  args(rulesFile.absolutePath)
  args("--lib")
  args(javaHome)

  args(embeddedJarFile.absolutePath)
}

abstract class DownloadR8Task: DefaultTask() {
  @get:Input
  abstract val sha1: Property<String>

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @TaskAction
  fun taskAction() {
    if (outputFile.get().asFile.exists()) {
      return
    }

    val url = "https://storage.googleapis.com/r8-releases/raw/main/${sha1.get()}/r8.jar"
    val request = Request.Builder()
      .get()
      .url(url)
      .build()

    OkHttpClient().newCall(request).execute().use { response ->
      check(response.isSuccessful) {
        "Cannot download $url (code=${response.code}): ${response.body?.string()}"
      }

      outputFile.get().asFile.outputStream().sink().buffer().use {
        it.writeAll(response.body!!.source())
      }
    }
  }
}

abstract class Holder @Inject constructor(val service: JavaToolchainService)

fun javaToolchainService(): JavaToolchainService {
  return objects.newInstance(Holder::class.java).service
}

tasks.register("run", JavaExec::class) {
  dependsOn(relocatedJarTaskProvider)
  classpath(files(relocatedJarFile))
  mainClass.set("com.example.MainKt")
}

tasks.withType<KotlinCompile> {
  compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
}

tasks.withType<JavaCompile> {
  options.release.set(8)
}

tasks.register("run2", JavaExec::class) {
  dependsOn(embeddedJarTaskProvider)
  classpath(files(embeddedJarFile))
  mainClass.set("com.example.MainKt")
}