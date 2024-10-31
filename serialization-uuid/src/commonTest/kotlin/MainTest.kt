import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class MainTest {
  @Test
  fun test() {
    Json.parseToJsonElement("{}")

    val client = HttpClient()
    runBlocking {
      client.get("https://toto.com")
    }
  }
}

@Serializable
class Toto(val a: String)