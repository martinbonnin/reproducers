import org.gradle.testkit.runner.GradleRunner
import java.io.File
import kotlin.test.Test

class MainTest {
  @Test
  fun test() {
    repeat(100) {
      val out = File("build/testProject")

      out.deleteRecursively()
      File("testProject").copyRecursively(out)

      println("iteration $it")
      GradleRunner.create()
        .withArguments("build")
        .withProjectDir(out)
        .build()
    }
  }
}