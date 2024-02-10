Defaults.itSettings

Test / parallelExecution := false

IntegrationTest / parallelExecution := false

Test / scalacOptions ++= Seq("-Yrangepos")

// Test
libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test

libraryDependencies += "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "4.12.2" % Test

val PekkoVersion = "1.0.2"
libraryDependencies += "org.apache.pekko" %% "pekko-slf4j"  % PekkoVersion

libraryDependencies += "org.apache.pekko" %% "pekko-stream" % PekkoVersion

libraryDependencies += "org.apache.pekko" %% "pekko-http"   % "1.0.1" % Test



val sttClientVersion = "3.9.3"
libraryDependencies += "com.softwaremill.sttp.client3" %% "pekko-http-backend" % sttClientVersion % Test
libraryDependencies += "com.softwaremill.sttp.client3" %% "core"              % sttClientVersion % Test