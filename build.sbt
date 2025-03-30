import sbt.url

name := jsonHandler.value.stringValue("package.json", "name")

organization := jsonHandler.value.stringValue("package.json", "organization")

val MongoCampHomepage = "https://www.mongocamp.dev"

organizationHomepage := Some(url(MongoCampHomepage))

homepage := Some(url("https://micrometer-mongodb.mongocamp.dev"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/MongoCamp/micrometer-mongodb"),
    "scm:https://github.com/MongoCamp/micrometer-mongodb.git"
  )
)

developers := List(
  Developer(
    id = "mongocamp",
    name = "MongoCamp-Team",
    email = "info@mongocamp.dev",
    url = url(MongoCampHomepage)
  ),
  Developer(
    id = "sfxcode",
    name = "Tom",
    email = "tom@mongocamp.dev",
    url = url(MongoCampHomepage)
  ),
  Developer(
    id = "quadstingray",
    name = "QuadStingray",
    email = "simon@mongocamp.dev",
    url = url(MongoCampHomepage)
  )
)

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

crossScalaVersions := Seq("3.6.4", "2.13.16")

scalaVersion := crossScalaVersions.value.head

scalacOptions += "-deprecation"

Test / parallelExecution := false

lazy val mongodb = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys ++= Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "dev.mongocamp"
  )

buildInfoOptions += BuildInfoOption.BuildTime

resolvers += "Sonatype OSS Snapshots".at("https://oss.sonatype.org/content/repositories/snapshots")

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.5.18"

libraryDependencies += "dev.mongocamp" %% "mongodb-driver" % "3.0.2"

libraryDependencies += "io.micrometer" % "micrometer-core" % "1.14.5"

libraryDependencies += "com.github.blemale" %% "scaffeine" % "5.3.0"

buildInfoPackage := "dev.mongocamp.driver.mongodb"

buildInfoOptions += BuildInfoOption.BuildTime

scalafmtOnCompile := false

coverageMinimumStmtTotal := 70

coverageFailOnMinimum := true

jsonFiles += (baseDirectory.value / "package.json")
