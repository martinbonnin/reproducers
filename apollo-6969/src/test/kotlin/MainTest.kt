import com.apollographql.apollo.api.json.BufferedSourceJsonReader
import com.apollographql.apollo.api.parseResponse
import com.example.GetItemQuery
import okio.Buffer
import kotlin.test.Test

class MainTest {
  @Test
  fun test() {
    val reader = BufferedSourceJsonReader(Buffer().writeUtf8("""
      {
        "data": {
          "item": {
            "id": null
          }
        }
      }
    """.trimIndent()))
    val response = GetItemQuery().parseResponse(reader)
    check(response.data!!.item == null)
  }
}