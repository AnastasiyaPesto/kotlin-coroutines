package coroutineContext

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {
  val context1: CoroutineContext = Dispatchers.Default + CoroutineName("Coroutine #1") + Job()
  println(context1)

  val context2: CoroutineContext = Dispatchers.IO + CoroutineName("Coroutine #2") + Job()
  println(context2)

  val context3: CoroutineContext = Dispatchers.Main + CoroutineName("Coroutine #3") + Job()
  println(context3)

  println(context1 + context2 + context3)
}