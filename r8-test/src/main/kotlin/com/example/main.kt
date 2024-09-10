package com.example


data class FooBar(val foo: String)

fun main() {
  println(className<FooBar>())
}

private inline fun <reified T> className(): String? {
  return T::class.simpleName
}

//fun main() {
//  println(FooBar::class.simpleName)
//}