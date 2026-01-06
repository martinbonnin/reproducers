import io.ktor.http.ContentType
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.delay
import io.ktor.server.cio.CIO as ServerCIO

val server = embeddedServer(ServerCIO, port = 8080) {
  routing {

    post("/graphql") {
      // Add 500ms delay before responding
      System.out.flush()
      delay(2000)
      call.respondText(
        """
            {
              "data": {
                "hello": "world"
              }
            }
            """.trimIndent(),
        ContentType.Application.Json
      )
    }
  }
}

fun main() {
  server.start(wait = true)
}