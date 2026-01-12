plugins {
  kotlin("jvm") version "2.2.20"
}

group = "ru.zentsova"
version = "1.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}