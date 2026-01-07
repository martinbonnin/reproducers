import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.watch
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import test.HelloQuery
import kotlin.test.Test
import kotlin.time.measureTime

class ParallelRequestsTest {
    @Test
    fun test100ParallelRequests() = runBlocking {
        val apolloClient = ApolloClient.Builder()
            .dispatcher(Dispatchers.IO)
            .normalizedCache(MemoryCacheFactory())
            .serverUrl("http://localhost:8080/graphql")
            .build()

        // Launch 100 parallel requests
        val flows = (1..100).map {
            flowOf(apolloClient.query(HelloQuery()))
                .flatMapLatest {
                    it.watch()
                }
        }
        val time = measureTime {
            combine(flows)
            { it ->
                //Nothing
            }.collect {
                println("Work finished")
            }
        }

        println("Successfully executed 100 parallel requests in $time")
    }
}
