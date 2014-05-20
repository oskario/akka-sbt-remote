name := "AkkaRemoteSBT-server"

scalaVersion := "2.11.0-RC4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.0",
  "com.typesafe.akka" %% "akka-remote" % "2.3.0"
)

