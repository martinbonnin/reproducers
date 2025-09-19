# android-9-kmp

A reproducer for `addStaticSourceDirectory()` not working.

This project contains a `static` folder with a non-valid Kotlin file.

I would expect the project to fail compilation, but it is ignoring the `static` folder.

```
./gradlew build
BUILD SUCCESSFUL in 4s
```