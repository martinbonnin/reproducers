import com.apollographql.apollo.api.json.jsonReader
import com.apollographql.apollo.api.parseData
import com.example.GetFooQuery
import okio.Buffer
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
  @Test
  fun test() {
    // language=JSON
    val json = """
      {
        "foo": null
      }
    """.trimIndent()

    val data = Buffer().writeUtf8(json).jsonReader()
      .let {
        GetFooQuery().parseData(it)
      }

    assertEquals(null, data!!.foo)
  }
}