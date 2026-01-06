import com.apollographql.apollo.ApolloClient
import com.apollographql.mockserver.MockRequestBase
import com.apollographql.mockserver.MockResponse
import com.apollographql.mockserver.MockServer
import com.apollographql.mockserver.MockServerHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import test.HelloQuery
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTime

class ParallelRequestsTest {
  @Test
  fun test100ParallelRequests() = runBlocking {
    val mockServer = MockServer.Builder()
      .handler(object : MockServerHandler {
        override fun handle(request: MockRequestBase): MockResponse {
          // Add 500ms delay before responding
          Thread.sleep(500)
          return MockResponse.Builder().body(
            """
        {
          "data": {
            "hello": "world"
          }
        }
        """.trimIndent()
          ).build()
        }
      }
      )
      .build()
      .use { mockServer ->
        val apolloClient = ApolloClient.Builder()
          .serverUrl(mockServer.url())
          .build()
        // Launch 100 parallel requests
        val time = measureTime {
          val results = (1..100).map { index ->
            async {
              println(">$index")
              apolloClient.query(HelloQuery()).execute()
              println("<$index")
            }
          }.awaitAll()

        }

        println("Successfully executed 100 parallel requests in $time")
      }
  }
}
