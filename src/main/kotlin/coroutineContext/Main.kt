package coroutineContext

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main(): Unit = runBlocking {
  launch(Dispatchers.IO + CoroutineName("My coroutine")) {
    print(coroutineContext)
  }
}