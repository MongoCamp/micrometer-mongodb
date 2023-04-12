package dev.mongocamp.micrometer.mongodb

import better.files.{File, Resource}
import com.typesafe.scalalogging.LazyLogging
import dev.mongocamp.driver.mongodb._
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
      MongoTestServer.providerMetrics.dao("restaurant").importJsonFile(File(Resource.getUrl("json/restaurant.json"))).result()
      MongoTestServer.providerMetrics.dao("pokedex").importJsonFile(File(Resource.getUrl("json/pokedex.json"))).result()
    }
  }
}
