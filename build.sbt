// so we can use keywords from Android, such as 'Android' and 'proguardOptions'
import android.Keys._
import android.Dependencies.{apklib,aar}

android.Plugin.androidBuild

name := "Reactive"

platformTarget in Android := "android-19"

versionCode in Android := Some(7)

versionName in Android := Some("1.0")


// pick the version of scala you want to use
scalaVersion := "2.10.3"

// scala 2.10 flag for feature warnings
scalacOptions in Compile ++= Seq(
  "-feature",
  "-language:postfixOps",
  "-language:implicitConversions"
)

proguardOptions in Android ++= Seq(
  "-dontobfuscate",
  "-dontoptimize",
  "-keep class com.squareup.** { *; }",
  "-keep class retrofit.** { *; }",
  "-keep class retrofit.http.** { *; }",
  "-keep class retrofit.client.** { *; }",
  "-keep class com.squareup.okhttp.** { *; }",
  "-keep interface com.squareup.okhttp.** { *; }",
  "-keep class com.google.gson.** { *; }",
  "-keep class com.google.inject.* { *; }",
  "-keep class org.apache.http.* { *; }",
  "-keep class org.codehaus.mojo.** { *; }",
  "-keep class org.apache.james.mime4j.* { *; }",
  "-keep class javax.inject.* { *; }",
  "-keep class sun.misc.Unsafe { *; }",
  "-keepattributes *Annotation*",
  "-keepclasseswithmembers class * { @retrofit.http.* <methods>; }",
  "-keepattributes Signature",
  "-dontwarn com.squareup.**",
  "-dontwarn android.**",
  "-dontwarn com.android.**",
  "-dontwarn com.google.appengine.**",
  "-dontwarn java.nio.**",
  "-dontwarn org.codehaus.mojo.**",
  "-dontwarn org.apache.**",
  "-dontwarn sun.misc.Unsafe"
)

libraryDependencies ++= Seq(
  "org.scaloid" %% "scaloid" % "3.2.1-8",
  "com.netflix.rxjava" % "rxjava-core" % "0.19.1",
  "com.netflix.rxjava" % "rxjava-scala" % "0.19.1" intransitive(),
  "com.netflix.rxjava" % "rxjava-android" % "0.19.1" intransitive(),
  "com.squareup.okhttp" % "okhttp" % "2.0.0",
  "com.squareup.okhttp" % "okhttp-urlconnection" % "2.0.0",
  "com.squareup.retrofit" % "retrofit" % "1.6.1",
  "com.squareup.picasso" % "picasso" % "2.3.3",
  "com.android.support" % "appcompat-v7" % "19.1.0"
)

// call install and run without having to prefix with android:
run <<= run in Android

install <<= install in Android

