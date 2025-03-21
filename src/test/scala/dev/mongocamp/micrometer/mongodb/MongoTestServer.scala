package dev.mongocamp.micrometer.mongodb

import com.typesafe.scalalogging.LazyLogging
import dev.mongocamp.driver.mongodb.database.DatabaseProvider
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.GenericContainer
import scala.concurrent.duration.DurationInt
import scala.concurrent.Await
import scala.concurrent.Future
import scala.jdk.CollectionConverters._
import scala.jdk.DurationConverters._
import sttp.capabilities
import sttp.capabilities.pekko.PekkoStreams
import sttp.client3._
import sttp.client3.pekkohttp.PekkoHttpBackend
import sttp.model.Method

object MongoTestServer extends LazyLogging {
  var port: Int = 4711

  lazy val provider: DatabaseProvider        = DatabaseProvider.fromPath("unit.test.mongo")
  lazy val providerMetrics: DatabaseProvider = DatabaseProvider.fromPath("unit.test.mongo.metrics")

  private var running: Boolean                                                             = false
  private lazy val backend: SttpBackend[Future, PekkoStreams with capabilities.WebSockets] = PekkoHttpBackend()

  private lazy val containerConfiguration: GenericContainer[_] = {
    val mongoDbContainer = new GenericContainer(s"mongocamp/mongodb:latest")
    mongoDbContainer.withExposedPorts(27017)
    mongoDbContainer.waitingFor(Wait.forLogMessage("(.*?)child process started successfully, parent exiting(.*?)", 2).withStartupTimeout(60.seconds.toJava))
    mongoDbContainer
  }

  def isRunning: Boolean = running

  def checkForLocalRunningMongoDb(): Unit = {
    if (!running) {
      try {
        val checkRequest   = basicRequest.method(Method.GET, uri"http://localhost:$port").response(asString)
        val resultFuture   = backend.send(checkRequest)
        val responseResult = Await.result(resultFuture, 1.seconds).body.getOrElse("not found")
        if (responseResult.contains("HTTP on the native driver port.")) {
          println("Use local running MongoDb")
          running = true
        }
      }
      catch {
        case e: Exception =>
          e.getMessage
      }
    }

  }

  def startMongoDatabase(): Unit = {
    checkForLocalRunningMongoDb()
    if (!running) {
      try {
        val ports: List[String] = List(port).map(
          i => s"$i:27017/tcp"
        )
        containerConfiguration.setPortBindings(ports.asJava)
        containerConfiguration.start()
      }
      catch {
        case _: Throwable =>
      }
      running = true
      sys.addShutdownHook {
        println("Shutdown for MongoDB Server triggered.")
        stopMongoDatabase()
      }

    }
  }

  def stopMongoDatabase(): Unit = {
    if (running) {
      containerConfiguration.stop()
      running = false
    }
  }

}
