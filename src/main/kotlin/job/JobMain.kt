package job

import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
  val job1 = Job()

  val job = launch(start = LAZY) { delay(2000) }

  println("(1) После создания: ${jobState(job)}")

  job.start()
  println("(2) После запуска: ${jobState(job)}")

  delay(500)
  println("(3) Во время выполнения: ${jobState(job)}")

//  job.cancel()
//  println("(4) После отмены: ${jobState(job)}")

  job.join()
  println("(5) После join(): ${jobState(job)}")
}

fun jobState(job: Job): String {
  val active = job.isActive
  val cancelled = job.isCancelled
  val completed = job.isCompleted

  return when {
    !active && !cancelled && !completed -> "New"
    active && !cancelled && !completed -> "Active / Completing"
    !active && !cancelled && completed -> "Completed"
    !active && cancelled && !completed -> "Cancelling"
    !active && cancelled && completed -> "Cancelled"
    else -> "Невероятно, но факт! (isActive=$active, isCompleted=$completed, isCancelled=$cancelled)"
  }
}

