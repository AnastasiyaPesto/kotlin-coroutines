package dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.measureTime

fun main() {
  val cores = Runtime.getRuntime().availableProcessors()
  println("Available cores count: $cores")

  // размеры матриц: A[m x k] и B[k x n]
  val m = 30
  val k = 20
  val n = 10
  val matrixA = Array(m) { IntArray(k) { Random.nextInt(1, 101) } }
  val matrixB = Array(k) { IntArray(n) { Random.nextInt(1, 101) } }

  val result: Array<IntArray>
  val time = measureTime {
    runBlocking {
      result = multiplyMatricesSuspend(matrixA, matrixB)
    }

//    result = multiplyMatrices(matrixA, matrixB)
  }

  println("Выполнено за $time мс")

  for (row in result) {
    println(row.joinToString(separator = " "))
  }
}

// Available cores count: 12
// Выполнено за 82.382320ms мс
suspend fun multiplyMatricesSuspend(
  matrixA: Array<IntArray>,
  matrixB: Array<IntArray>,
): Array<IntArray> = coroutineScope {
  val m = matrixA.size
  val n = matrixB[0].size
  val result = Array(m) { IntArray(n) }

  val jobs = (0 until m).map { row ->
    async(Dispatchers.Default) {
      println("Обработка строки #$row на потоке ${Thread.currentThread().name}")
      for (col in 0 until n) {
        var sum = 0
        for (k in 0 until matrixA[row].size) {
          sum += matrixA[row][k] * matrixB[k][col]
        }
        result[row][col] = sum
      }
    }
  }

  jobs.awaitAll()
  result
}

// Available cores count: 12
// Выполнено за 3.579505ms мс
fun multiplyMatrices(
  matrixA: Array<IntArray>,
  matrixB: Array<IntArray>,
): Array<IntArray> {
  val m = matrixA.size
  val n = matrixB[0].size
  val result = Array(m) { IntArray(n) }

  for (i in 0..< result.size) {
    println("Обработка строки #$i на потоке ${Thread.currentThread().name}")
    for (j in 0..< result[0].size) {
      result[i][j] = 0
      for (k in 0..<matrixA[0].size) {
        result[i][j] += matrixA[i][k] * matrixB[k][j]
      }
    }
  }
  return result
}