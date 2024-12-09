import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.ws.SubscriptionWsProtocol
import com.apollographql.apollo.network.ws.WebSocketNetworkTransport
import com.example.client.GetCountSubscription
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class MainTest {
  @Test
  fun test() {
    val server = startServer()

    ApolloClient.Builder().apply {
      serverUrl("http://localhost:8080/graphqls")
      subscriptionNetworkTransport(
//        WebSocketNetworkTransport.Builder()
//          .serverUrl("https://reviews-483667740457.europe-west9.run.app/subscription")
//          .protocol(
//            SubscriptionWsProtocol.Factory(
//              connectionPayload = {
//                mapOf("Authorization" to "woo")
//              }
//            )
//          )
//          .build()
//        HttpNetworkTransport.Builder()
//          .serverUrl("https://router-483667740457.europe-west9.run.app/")
//          .build()
        WebSocketNetworkTransport.Builder()
          .serverUrl("http://localhost:8080/subscription")
          .protocol(
            SubscriptionWsProtocol.Factory(
              connectionPayload = {
                mapOf("Authorization" to "woo")
              }
            )
          )
          .build()
      )
      autoPersistedQueries()
    }.build().use { apolloClient ->
      runBlocking {
        apolloClient.subscription(GetCountSubscription())
          .toFlow().collect {
            println("Got ${it.data}")
          }
      }
    }

    server.stop()
  }
}