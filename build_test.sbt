Defaults.itSettings

Test / parallelExecution := false

IntegrationTest / parallelExecution := false

Test / scalacOptions ++= Seq("-Yrangepos")

// Test
libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test

libraryDependencies += "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "4.6.3" % Test

val AkkaVersion = "2.6.20" // Last Apache Licence Version
val AkkaHttpVersion = "10.2.10" // Last Apache Licence Version
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion % Test
)

val sttClientVersion = "3.8.15"
libraryDependencies += "com.softwaremill.sttp.client3" %% "akka-http-backend" % sttClientVersion % Test
libraryDependencies += "com.softwaremill.sttp.client3" %% "core"              % sttClientVersion % Test