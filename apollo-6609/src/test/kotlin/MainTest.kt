import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.cache.normalized.ApolloStore
import com.apollographql.apollo3.cache.normalized.CacheOnlyInterceptor
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.NetworkOnlyInterceptor
import com.apollographql.apollo3.cache.normalized.api.FieldPolicyCacheResolver
import com.apollographql.apollo3.cache.normalized.api.TypePolicyCacheKeyGenerator
import com.apollographql.apollo3.cache.normalized.cacheInfo
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicyInterceptor
import com.apollographql.apollo3.cache.normalized.refetchPolicyInterceptor
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.store
import com.apollographql.apollo3.cache.normalized.watch
import com.apollographql.apollo3.exception.CacheMissException
import com.apollographql.apollo3.exception.apolloExceptionHandler
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.apollographql.apollo3.mockserver.MockServer
import com.apollographql.apollo3.mockserver.enqueue
import example.com.GetObjectsQuery
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
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
        .configureNormalizedCache()
        .build()
        .use { apolloClient ->

          mockServer.enqueue(
            // language=json
            """
            {
             "data": { "objects": [] }
            }
          """.trimIndent()
          )
          mockServer.enqueue(
            // language=json
            """
            {
             "data": { "objects": [{"__typename": "ConcreteObject1", "id": "42", "name": "foo", "model": "model42"}] }
            }
          """.trimIndent()
          )
          mockServer.enqueue(
            // language=json
            """
            {
             "data": { "objects": [{"__typename": "ConcreteObject1", "id": "42", "name": "foo", "model": "model42"}, {"__typename": "ConcreteObject2", "id": "43", "name": "foo2", "model":  "some"}] }
            }
          """.trimIndent()
          )
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

          delay(1.seconds)
          println("updating the cache from the network a second time...")
          apolloClient.query(GetObjectsQuery())
            .fetchPolicy(FetchPolicy.NetworkOnly)
            .execute()

        }
      mockServer.stop()
    }
  }
}

private class CacheMissLoggingInterceptor(private val log: (ApolloRequest<*>, String) -> Unit) :
  ApolloInterceptor {
  override fun <D : Operation.Data> intercept(
    request: ApolloRequest<D>,
    chain: ApolloInterceptorChain
  ): Flow<ApolloResponse<D>> = chain.proceed(request).onEach { response ->
    response.cacheInfo?.cacheMissException?.message?.let { message ->
      log(request, message)
    }
  }.catch { throwable ->
    if (throwable is CacheMissException) {
      log(request, throwable.message.toString())
    }
    throw throwable
  }
}

private fun ApolloClient.Builder.logCacheMisses(
  log: (ApolloRequest<*>, String) -> Unit,
): ApolloClient.Builder = addInterceptor(CacheMissLoggingInterceptor(log))

@OptIn(ApolloExperimental::class)
fun ApolloClient.Builder.configureNormalizedCache(): ApolloClient.Builder = logCacheMisses { request, message ->
  println("Cache miss for request ${request.operation}: $message.")
}
  .store(
    store = ApolloStore,
    writeToCacheAsynchronously = false,
  )
  .fetchPolicyInterceptor(NetworkOnlyInterceptor)
  .refetchPolicyInterceptor(CacheOnlyInterceptor)
  .also {
    apolloExceptionHandler = { exception ->
      println("Apollo exception: $exception")
    }
  }

val sqlNormalizedCacheFactory = SqlNormalizedCacheFactory()

private val ApolloStore: ApolloStore = ApolloStore(
  normalizedCacheFactory = sqlNormalizedCacheFactory,
  cacheKeyGenerator = TypePolicyCacheKeyGenerator,
  cacheResolver = FieldPolicyCacheResolver,
)

