package coroutineBuilders

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main() {
  runBlocking {
    val deferred1 = async {
      delay(400)
      "Result deferred1"
    }

    val deferred2 = async {
      delay(200)
      "Result deferred2"
    }

    val result = select {
      deferred1.onAwait { r -> "Done first: $r"}
      deferred2.onAwait { r -> "Done first: $r"}
    }

    println(result)
  }
}