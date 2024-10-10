package com.example

fun main() {
  startTheDay { who ->
    println("Hello $who")
  }

  object : Greeter{
    override fun hello(who: String) {

    }
  }.hello("World")
}

fun startTheDay(greeter: Greeter) {
  greeter.hello("World")
}

fun interface Greeter {
  fun hello(who: String)
}