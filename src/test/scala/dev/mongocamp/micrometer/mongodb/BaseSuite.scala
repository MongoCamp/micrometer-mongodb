package dev.mongocamp.micrometer.mongodb

import better.files.File
import better.files.Resource
import com.typesafe.scalalogging.LazyLogging
import dev.mongocamp.driver.mongodb._
import scala.util.Try
import scala.util.Using
class BaseSuite extends munit.FunSuite with LazyLogging {

  override def beforeAll(): Unit = {
    resetDatabase()
  }

  override def afterAll(): Unit = {
    resetDatabase()
  }

  private def resetDatabase(): Unit = {
    if (!MongoTestServer.isRunning) {
      MongoTestServer.startMongoDatabase()
    }

    MongoTestServer.providerMetrics
      .collectionNames()
      .foreach(
        colName => {
          Try {
            MongoTestServer.providerMetrics.collection(colName).drop().result()
          }
        }
      )
    MongoTestServer.provider
      .collectionNames()
      .foreach(
        colName => {
          Try {
            MongoTestServer.providerMetrics.collection(colName).drop().result()
          }
        }
      )

    MongoTestServer.providerMetrics.dao("restaurant").importJsonFile(resourceFile("json/restaurant.json")).result()
    MongoTestServer.providerMetrics.dao("pokedex").importJsonFile(resourceFile("json/pokedex.json")).result()

  }

  // classpath resources can live inside a jar, so copy them to a real file
  private def resourceFile(name: String): File = {
    val file = File.newTemporaryFile(suffix = ".json")
    file.deleteOnExit()
    file.writeByteArray(Using.resource(Resource.getAsStream(name))(_.readAllBytes()))
  }
}
