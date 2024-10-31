import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import kotlin.test.Test

class MainTest {
  @Test
  fun test(): Unit = runBlocking {
    withContext(Dispatchers.Default.limitedParallelism(1)) {
      val dispatcher = Dispatchers.IO
      val selectorManager = SelectorManager(dispatcher)
      var serverSocket: ServerSocket = aSocket(selectorManager).tcp().bind("127.0.0.1", 0)
      launch(start = CoroutineStart.UNDISPATCHED) {
        val socket = serverSocket.accept()

        socket.openWriteChannel().use {
          writeString("Hello World")
        }
      }
      runClient((serverSocket.localAddress as InetSocketAddress).port)
    }
    println("success")
  }

  private suspend fun runClient(port: Int) {
    HttpClient().use {
      try {
        it.get("http://127.0.0.1:$port")
      } catch (e: Exception) {
        throw e
      }
    }
  }
}