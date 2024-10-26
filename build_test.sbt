Defaults.itSettings

Test / parallelExecution := false

IntegrationTest / parallelExecution := false

Test / scalacOptions ++= Seq("-Yrangepos")

// Test
libraryDependencies += "org.scalameta" %% "munit" % "1.0.2" % Test

libraryDependencies += "org.testcontainers" % "testcontainers" % "1.20.3"

val PekkoVersion = "1.1.2"
libraryDependencies += "org.apache.pekko" %% "pekko-slf4j"  % PekkoVersion

libraryDependencies += "org.apache.pekko" %% "pekko-stream" % PekkoVersion

libraryDependencies += "org.apache.pekko" %% "pekko-http"   % "1.1.0" % Test



val sttClientVersion = "3.10.1"
libraryDependencies += "com.softwaremill.sttp.client3" %% "pekko-http-backend" % sttClientVersion % Test
libraryDependencies += "com.softwaremill.sttp.client3" %% "core"              % sttClientVersion % Test