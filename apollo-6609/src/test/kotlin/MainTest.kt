import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.MemoryCache
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.watch
import com.apollographql.apollo3.mockserver.MockServer
import com.apollographql.apollo3.mockserver.enqueue
import example.com.GetObjectsQuery
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

class MainTest {
  @kotlin.test.Test
  fun test() {
    runBlocking {
      val mockServer = MockServer()

      ApolloClient.Builder()
        .serverUrl(mockServer.url())
        .normalizedCache(MemoryCacheFactory())
        .build()
        .use { apolloClient ->

          mockServer.enqueue(
            // language=json
            """
            {
             "data": { "objects": [] }
            }
          """.trimIndent())
          mockServer.enqueue(
            // language=json
            """
            {
             "data": { "objects": [{"__typename": "Object", "id": "42", "name": "foo"}] }
            }
          """.trimIndent())
          launch {
            apolloClient.query(GetObjectsQuery())
              .watch()
              .collect {
                println("Got ${it.data?.objects}")
              }
          }

          delay(1.seconds)
          println("updating the cache from the network...")
          apolloClient.query(GetObjectsQuery())
            .fetchPolicy(FetchPolicy.NetworkOnly)
            .execute()

        }
      mockServer.stop()
    }
  }
}