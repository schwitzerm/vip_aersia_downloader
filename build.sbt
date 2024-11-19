ThisBuild / scalaVersion := "2.11.8"

name := "vip_aersia_downloader"
version := "0.0.3-SNAPSHOT"
libraryDependencies ++= Seq(
  //scala
  "jline" % "jline" % "2.14.6",
  "org.scala-lang.modules" %% "scala-xml" % "1.3.1",

  //akka
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11.2",
  "com.typesafe.akka" %% "akka-stream" % "2.5.32",

  //google
  "com.google.inject" % "guice" % "7.0.0",

  //time
  "com.github.nscala-time" %% "nscala-time" % "2.32.0"
)
