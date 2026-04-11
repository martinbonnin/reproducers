import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.isFromCache
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.watch
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.apollographql.apollo3.mockserver.MockServer
import com.apollographql.apollo3.mockserver.enqueue
import com.apollographql.apollo3.network.http.DefaultHttpEngine
import com.apollographql.apollo3.network.http.HttpNetworkTransport
import com.example.GetFooQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class MainTest {
  @Test
  fun test() = runBlocking {
    ApolloClient.Builder()
      .serverUrl("http://localhost:8089")
      .normalizedCache(MemoryCacheFactory())
      .addInterceptor(ApolloLoggingInterceptor())
      .build()
      .use { apolloClient: ApolloClient ->
        apolloClient.query(GetFooQuery()).watch().collect {
          println("got:" + it.data)
        }
      }
  }
}

class ApolloLoggingInterceptor : ApolloInterceptor {
  override fun <D : Operation.Data> intercept(
    request: ApolloRequest<D>,
    chain: ApolloInterceptorChain,
  ): Flow<ApolloResponse<D>> {
    return chain
      .proceed(request)
      .onStart {
        with(request) {
          println("Request (name: ${operation.name()}, id: $requestUuid, httpMethod: $httpMethod)")
        }
      }
      .onEach { response ->
        with(response) {
          println("Response (name: ${operation.name()}, id: $requestUuid, fromCache: $isFromCache, errors:$errors)")
        }
      }
  }
}