import com.apollographql.apollo.ApolloClient
import foo.ExampleQuery
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class MainTest {
  @Test
  fun test() {
    runBlocking {
      val apollo = ApolloClient.Builder().build()
      apollo.query(ExampleQuery())
        .execute()
        .dataOrThrow()
        .foo
        .bar
        .id // bar is @catch(to: THROW) and bar is non-null
    }
  }
}