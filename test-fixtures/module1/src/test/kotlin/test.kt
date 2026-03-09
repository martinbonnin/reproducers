import org.junit.Test

class HelloTest {
    @Test
    fun test() {
        assert(hello == expectedHello)
        assert(hello == generatedTestFixture)
    }
}