package package1

import java.nio.file.Path

class Something(private val path: Path)

fun Path.Something(): Something {
  return Something(this)
}