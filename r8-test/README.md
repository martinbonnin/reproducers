# Reproducer for a R8 issue

Run `./gradlew run` to reproduce the issue:

```
> Task :run FAILED
[...]
Exception in thread "main" java.lang.IllegalAccessError: class com.example.MainKt tried to access private field kotlin.jvm.internal.Reflection.factory (com.example.MainKt and kotlin.jvm.internal.Reflection are in unnamed module of loader 'app')
	at com.example.MainKt.main(SourceFile:3)
	at com.example.MainKt.main(SourceFile:1)
```

`build/mapping.txt` indicates relocation did not happen:

```
com.example.FooBar -> com.example.FooBar:
...
```

Change the R8 version to [the parent of 94e653494d92f080b38e81e88036c5016b01b334](https://r8.googlesource.com/r8/+/26aaf90cc10cc79b86fb568f959470853c4042be):

```diff
--- a/r8-test/build.gradle.kts
+++ b/r8-test/build.gradle.kts
@@ -13,7 +13,7 @@ plugins {
  * This is the commit that introduced the regression. Try 26aaf90cc10cc79b86fb568f959470853c4042be for a commit that works.
  */
 val downloadTaskProvider = tasks.register("downloadR8", Download::class) {
-  val sha1 = "94e653494d92f080b38e81e88036c5016b01b334"
+  val sha1 = "26aaf90cc10cc79b86fb568f959470853c4042be"
   src("https://storage.googleapis.com/r8-releases/raw/main/$sha1/r8.jar")
 
   dest(layout.buildDirectory.file("$sha1.jar"))
```

Run `./gradlew run` again:

```
> Task :run
FooBar
[...]
BUILD SUCCESSFUL in 3s
```

`build/mapping.txt` indicates relocation did happen:

```
com.example.FooBar -> relocated.com.example.FooBar:
...
```