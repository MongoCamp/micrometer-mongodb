Defaults.itSettings

Test / parallelExecution := false

IntegrationTest / parallelExecution := false

Test / scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, _)) => Seq("-Yrangepos")
    case _            => Seq.empty
  }
}

// Test
libraryDependencies += "org.scalameta" %% "munit" % "1.3.6" % Test

libraryDependencies += "org.testcontainers" % "testcontainers" % "2.0.5"

val PekkoVersion = "1.7.1"
libraryDependencies += "org.apache.pekko" %% "pekko-slf4j"  % PekkoVersion

libraryDependencies += "org.apache.pekko" %% "pekko-stream" % PekkoVersion

libraryDependencies += "org.apache.pekko" %% "pekko-http"   % "1.4.2" % Test



val sttClientVersion = "3.11.0"
libraryDependencies += "com.softwaremill.sttp.client3" %% "pekko-http-backend" % sttClientVersion % Test
libraryDependencies += "com.softwaremill.sttp.client3" %% "core"              % sttClientVersion % Test