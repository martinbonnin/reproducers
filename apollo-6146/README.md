Reproducer for https://github.com/apollographql/apollo-kotlin/issues/6146

Try to publish:

```
./gradlew publishToMavenLocal
```

Get an error:

```
FAILURE: Build failed with an exception.

* What went wrong:
A problem was found with the configuration of task ':sourceReleaseJar' (type 'SourceJarTask').
  - Gradle detected a problem with the following location: '/Users/martinbonnin/git/reproducers/apollo-6146/build/generated-source'.
    
    Reason: Task ':sourceReleaseJar' uses this output of task ':generateSource' without declaring an explicit or implicit dependency. This can lead to incorrect results being produced, depending on what order the tasks are executed.
    
    Possible solutions:
      1. Declare task ':generateSource' as an input of ':sourceReleaseJar'.
      2. Declare an explicit dependency on ':generateSource' from ':sourceReleaseJar' using Task#dependsOn.
      3. Declare an explicit dependency on ':generateSource' from ':sourceReleaseJar' using Task#mustRunAfter.
```