package job

import kotlinx.coroutines.*

fun main() = runBlocking {
  val parent = launch {
    val child1 = launch {
      try {
        println("Дочерняя корутина 1 запущена")
        delay(1000)
        println("Дочерняя корутина 1 завершена")
      } catch (e: CancellationException) {
        println("Дочерняя корутина 1 отменена по причине: ${e.message}")
        throw e  // Перевыбрасываем, чтобы отмена корректно распространилась по иерархии
      } finally {
        println("Блок finally для дочерней корутины 1")
      }
    }

    val child2 = launch {
      try {
        println("Дочерняя корутина 2 запущена")
        delay(1500)
        println("Дочерняя корутина 2 завершена")
      } catch (e: CancellationException) {
        println("Дочерняя корутина 2 отменена по причине: ${e.message}")
        throw e
      } finally {
        println("Блок finally для дочерней корутины 2")
      }
    }

    // Подождем, чтобы дочерние корутины успели начать работу
    delay(500)
    println("Отмена родительской корутины")
    cancel("Отмена родительской корутины") // подразумевается CoroutineScope.cancel()
  }
}