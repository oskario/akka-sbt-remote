name := "AkkaRemoteSBT"

version := "1.0"

scalaVersion := "2.11.0-RC4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.0",
  "com.typesafe.akka" %% "akka-remote" % "2.3.0",
  "com.typesafe" % "config" % "1.2.0" 
)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

//javaOptions += "-Xnojline"

lazy val shared = project

lazy val server = project dependsOn(shared)

lazy val client = project dependsOn(shared)

connectInput in run := true

fork in run := true