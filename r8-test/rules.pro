#noinspection ShrinkerUnresolvedReference
-keep class kotlin.Metadata { *; }

-keepattributes Signature,InnerClasses,EnclosingMethod
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep class com.example.MainKt { *; }

-dontusemixedcaseclassnames
-dontobfuscate
-repackageclasses relocated
-allowaccessmodification
