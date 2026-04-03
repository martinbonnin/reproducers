import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.FetchPolicy
import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.fetchPolicy
import com.apollographql.apollo.cache.normalized.normalizedCache
import com.apollographql.apollo.testing.QueueTestNetworkTransport
import com.apollographql.apollo.testing.enqueueTestResponse
import com.example.GetFooQuery
import com.example.builder.Data
import com.example.builder.resolver.DefaultFakeResolver
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class MainTest {
  @Test
  fun test(): Unit = runBlocking {
    ApolloClient.Builder()
      .networkTransport(QueueTestNetworkTransport())
      .normalizedCache(MemoryCacheFactory())
      .build()
      .use { apolloClient ->
        val operation = GetFooQuery(false)
        val data = GetFooQuery.Data(DefaultFakeResolver()) {}
        apolloClient.enqueueTestResponse(operation, data)
        var response = apolloClient.query(operation).fetchPolicy(FetchPolicy.NetworkOnly).execute()
        assertNotNull(response.data!!.foo)
        response = apolloClient.query(operation).fetchPolicy(FetchPolicy.CacheOnly).execute()
        assertNull(response.data!!.foo)
      }
  }
}