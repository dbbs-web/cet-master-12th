# Proguard rules for CET Master 12th app

# Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Picasso
-keep class com.squareup.picasso.** { *; }

# PDF Viewer
-keep class com.github.barteksc.** { *; }

# App classes
-keep class com.cetmaster.app.** { *; }

# Custom application classes
-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve annotations
-keepattributes *Annotation*

# Preserve line numbers
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
