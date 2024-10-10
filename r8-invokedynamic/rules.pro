#noinspection ShrinkerUnresolvedReference
-keep class com.example.MainKt { *; }

-dontobfuscate
-repackageclasses relocated

# Allow to repackage com.moshi.JsonAdapter.lenient
-allowaccessmodification
