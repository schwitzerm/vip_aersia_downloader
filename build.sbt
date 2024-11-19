ThisBuild / scalaVersion := "2.13.15"

name := "vip_aersia_downloader"
version := "0.0.3-SNAPSHOT"
libraryDependencies ++= Seq(
  //scala
  "jline" % "jline" % "2.14.6",
  "org.scala-lang.modules" %% "scala-xml" % "2.2.0",

  //cats
  "org.typelevel" %% "cats-core" % "2.12.0",
  "org.typelevel" %% "cats-kernel" % "2.12.0",
  "org.typelevel" %% "cats-effect" % "3.5.5",

  //google
  "com.google.inject" % "guice" % "7.0.0",

  //time
  "com.github.nscala-time" %% "nscala-time" % "2.32.0"
)
