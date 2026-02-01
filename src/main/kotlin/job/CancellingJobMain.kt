package job

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.yield

suspend fun heavyCalculation2() = withContext(Dispatchers.Default) {
  repeat(100) { i ->
    yield() // точка приостановки
    println("Шаг: $i")
    Thread.sleep(10)
  }
}

suspend fun heavyCalculation3() = withContext(Dispatchers.Default) {
  repeat(100) { i ->
    if (coroutineContext[Job]?.isActive != true) return@repeat
    println("Шаг: $i")
    Thread.sleep(10)
  }
}

suspend fun heavyCalculation() = withTimeout(2000) {
  repeat(100) { i ->
    println("Шаг: $i")
    delay(1000)
  }
}

fun main() = runBlocking {
//  val job = launch { heavyCalculation() }
//  delay(50)
//  job.cancel()

  try {
    heavyCalculation()
  }
  catch (e: TimeoutCancellationException) {
    println("Время истекло, корутина отменена: ${e.message}")
  }
}