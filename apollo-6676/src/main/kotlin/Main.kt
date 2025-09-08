@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.Unit

suspend fun main() {
  val dispatcher = currentCoroutineContext()[CoroutineDispatcher]
  println("Current dispatcher: $dispatcher")
  delay2(1000)
  println("Hello World!")
  Exception().printStackTrace()
}

@OptIn(InternalCoroutinesApi::class)
public suspend fun delay2(timeMillis: Long) {
  if (timeMillis <= 0) return // don't delay
  return suspendCancellableCoroutine sc@ { cont: CancellableContinuation<Unit> ->
    Exception().printStackTrace()
    // if timeMillis == Long.MAX_VALUE then just wait forever like awaitCancellation, don't schedule.
    if (timeMillis < Long.MAX_VALUE) {
      cont.context.delay.scheduleResumeAfterDelay(timeMillis, cont)
    }
  }
}
