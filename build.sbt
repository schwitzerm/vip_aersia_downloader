name := "vip_aersia_downloader"

version := "0.0.1-SNAPSHOP"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  //scala
  "org.scala-lang.modules" %% "scala-xml" % "1.0.5",

  //akka
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
  "com.typesafe.akka" %% "akka-stream" % "2.4.12",

  //google
  "com.google.inject" % "guice" % "4.1.0"
)
