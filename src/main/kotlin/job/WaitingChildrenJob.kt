package job

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
  coroutineScope {
    val scopeJob = coroutineContext[Job]!!
    scopeJob.invokeOnCompletion {
      println("scopeJob дождался завершения своих потомков: isCompleted = ${scopeJob.isCompleted}")
    }

    launch { delay(300); println("Дочерняя корутина 1 завершена") }
    launch { delay(400); println("Дочерняя корутина 2 завершена") }
    launch { delay(500); println("Дочерняя корутина 3 завершена") }

    println("Внутри coroutineScope: scopeJob.isActive = ${scopeJob.isActive}, scopeJob.isCompleted = ${scopeJob.isCompleted}")
  }
  println("Завершение runBlocking")
}