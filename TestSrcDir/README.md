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

Go [here](https://github.com/martinbonnin/reproducers/blob/f4b787db1a3fadc5c3a4d740e936f0e7412cf8bf/TestSrcDir/app/build.gradle.kts#L83) and set the condition to false to use the other branch to make the test pass.