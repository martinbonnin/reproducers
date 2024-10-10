package app

import lib.MyAnnotation

@MyAnnotation
fun fooApp() {
  @MyAnnotation
  val bar = ""

  println("Hello world")
}
