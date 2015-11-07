name := "Main"

version := "1.0"

scalaVersion := "2.11.7"

showSuccess := false

Revolver.settings: Seq[sbt.Def.Setting[_]]

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"