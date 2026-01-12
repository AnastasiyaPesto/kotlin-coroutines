package coroutineScope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
  launch {
    delay(3000)
    print("beautiful ")
  }
  launch {
    delay(6000)
    print("World")
  }

  print("Hello, ")
}