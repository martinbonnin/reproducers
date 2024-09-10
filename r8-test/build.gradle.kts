import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.jetbrains.kotlin.jvm").version("2.0.20")
  id("de.undercouch.download").version("5.6.0")
}

/*
 * Download R8 from https://r8.googlesource.com/r8/+/94e653494d92f080b38e81e88036c5016b01b334
 *
 * This is the commit that introduced the regression. Try 26aaf90cc10cc79b86fb568f959470853c4042be for a commit that works.
 */
val downloadTaskProvider = tasks.register("downloadR8", Download::class) {
  val sha1 = "94e653494d92f080b38e81e88036c5016b01b334"
  src("https://storage.googleapis.com/r8-releases/raw/main/$sha1/r8.jar")

  dest(layout.buildDirectory.file("$sha1.jar"))

  overwrite(false)
}

val output = file("build/relocated.jar")

/*
 * Call R8 to build a fat jar with relocated dependencies, including kotlin-stdlib
 * (See rules.pro for more details)
 */
val relocateTaskProvider = tasks.register("relocate", JavaExec::class) {
  classpath(downloadTaskProvider)

  mainClass.set("com.android.tools.r8.R8")

  args("--release")
  args("--classfile")
  args("--output")
  args(output.absolutePath)
  args("--pg-map-output")
  args(file("build/mapping.txt").absolutePath)
  args("--pg-conf")
  args(file("rules.pro").absolutePath)
  args("--lib")

  val javaHome = javaToolchainService().compilerFor {
    languageVersion.set(JavaLanguageVersion.of(8))
  }.get().metadata.installationPath.asFile.absolutePath

  println("javaHome=$javaHome")
  args(javaHome)

  dependsOn("jar")
  args(file("build/libs/r8-test.jar").absolutePath)

  configurations.getByName("runtimeClasspath").files.forEach {
    args(it.absolutePath)
  }
}

val kotlin18 = configurations.detachedConfiguration()
kotlin18.dependencies.add(dependencies.create("org.jetbrains.kotlin:kotlin-stdlib:1.8.0"))

tasks.register("run", JavaExec::class) {
  dependsOn(relocateTaskProvider)

  /*
   * Prepend kotlin-stdlib:1.8 in the classpath
   * If kotlin-stdlib:2.0 was relocated, this should be fine but it is not and this
   * creates an issue.
   * See also build/mapping.txt, none of the classes are relocated
   */
  val kotlin18Files =  kotlin18.files
  println("kotlin files=${kotlin18Files}")
  classpath(*kotlin18Files.toTypedArray(), output)

  mainClass.set("com.example.MainKt")
}

/*
 * Boilerplate stuff
 */
tasks.withType(JavaCompile::class.java).configureEach {
  options.release.set(8)
}

tasks.withType(KotlinCompile::class.java).configureEach {
  compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
}

abstract class Holder @Inject constructor(val service: JavaToolchainService)

fun javaToolchainService(): JavaToolchainService {
  return objects.newInstance(Holder::class.java).service
}
