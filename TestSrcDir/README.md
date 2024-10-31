# Reproducer for AGP srcDir() issues

`Sources.kotlin.all` does not carry over task dependencies from `kotlin.sourceSets.getByName("main").srcDir`.

To reproduce:

```
./gradlew 

[...]

FAILURE: Build completed with 2 failures.

1: Task failed with an exception.
-----------
* What went wrong:
A problem occurred configuring project ':app'.
> java.lang.IllegalStateException: No dependencies were found for property property 'all'
```

Go [here](https://github.com/martinbonnin/reproducers/blob/24780220dc52b4dbe77d7f43b140f6f8dd8cfc4a/TestSrcDir/app/build.gradle.kts#L83) and set the condition to false to use the other branch to make the test pass.