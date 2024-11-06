``` 
$ ./gradlew --write-locks
[...]
BUILD SUCCESSFUL in 381ms
$ ./gradlew    

FAILURE: Build failed with an exception.

* Where:
Build file '/Users/martinbonnin/git/reproducers/gradle-exclusions/build.gradle.kts' line: 18

* What went wrong:
Could not resolve all files for configuration ':resolvable'.
> Resolved 'org.jetbrains.kotlin:kotlin-reflect:2.0.10' which is not part of the dependency lock state
```