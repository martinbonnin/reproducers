import com.apollographql.apollo.ApolloClient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import test.HelloQuery
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTime

class ParallelRequestsTest {
  @Test
  fun test100ParallelRequests() = runBlocking {
    val server = embeddedServer(CIO, port = 8080) {
      routing {
        var i = 0
        post("/graphql") {
          // Add 500ms delay before responding
          println("  got $i")
          System.out.flush()
          delay(500)
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
          println("  res $i")
        }
      }
    }.start(wait = false)

    try {
      val apolloClient = ApolloClient.Builder()
        .serverUrl("http://localhost:8080/graphql")
        .build()

      // Launch 100 parallel requests
      val time = measureTime {
        val results = (1..64).map { index ->
          async {
            println(">$index")
            System.out.flush()
            apolloClient.query(HelloQuery()).execute()
            println("<$index")
            System.out.flush()
          }
        }.awaitAll()
      }

      println("Successfully executed 100 parallel requests in $time")
    } finally {
      server.stop(1000, 2000)
    }
  }
}
