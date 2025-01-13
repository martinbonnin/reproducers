import org.gradle.testkit.runner.GradleRunner
import kotlin.test.Test

class MainTest {
  @Test
  fun test() {
    repeat(10) {
      val out = File("build/testProject")

      GradleRunner.create()
        .build()
    }
  }
}