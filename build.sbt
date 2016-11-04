name := "vip_aersia_downloader"

version := "0.0.2-SNAPSHOP"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  //scala
  "jline" % "jline" % "2.14.2",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.5",

  //akka
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
  "com.typesafe.akka" %% "akka-stream" % "2.4.12",

  //google
  "com.google.inject" % "guice" % "4.1.0",

  //time
  "com.github.nscala-time" %% "nscala-time" % "2.14.0"
)
