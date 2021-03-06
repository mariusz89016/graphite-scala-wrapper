import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.mariusz89016",
      scalaVersion := "2.12.6",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "graphite-scala-wrapper",
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.7",
      scalaTest % Test
    )
  )
