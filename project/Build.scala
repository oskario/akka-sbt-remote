import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object HelloBuild extends Build {
	lazy val buildSettings = Defaults.defaultSettings ++ Seq(
		// version := "0.1-SNAPSHOT",
		// organization := "com.example",
		// scalaVersion := "2.10.1"
	)

    lazy val server = Project(
    	id = "server", 
    	base = file("server"), 
    	settings = buildSettings ++ assemblySettings) settings(
      		// your settings here
    	) dependsOn(shared)

    lazy val client = Project(
    	id = "client", 
    	base = file("client"), 
    	settings = buildSettings ++ assemblySettings) settings(
      		// your settings here
    	) dependsOn(shared)

    lazy val shared = Project(
    	id = "shared", 
    	base = file("shared"))
}