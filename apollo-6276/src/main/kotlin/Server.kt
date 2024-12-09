import com.apollographql.execution.InMemoryPersistedDocumentCache
import com.apollographql.execution.annotation.GraphQLQuery
import com.apollographql.execution.annotation.GraphQLSubscription
import com.apollographql.execution.ktor.apolloModule
import com.apollographql.execution.ktor.apolloSubscriptionModule
import com.example.server.ServiceExecutableSchemaBuilder
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@GraphQLQuery
class Query {
  val hello = "world2"
}

@GraphQLSubscription
class Subscription {
  fun count(): Flow<Int> = flow {
    var i = 0
    while(true) {
      emit(i)
      delay(1000)
      i++
    }
  }
}

fun startServer(): EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration> {
  return embeddedServer(Netty, port = 8080) {
    // /graphql route
    val executableSchema = ServiceExecutableSchemaBuilder()
      .persistedDocumentCache(InMemoryPersistedDocumentCache())
      .build()
    apolloModule(executableSchema)
    apolloSubscriptionModule(executableSchema)
  }.start()
}