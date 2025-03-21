package dev.mongocamp.micrometer.mongodb.binder

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.driver.mongodb.database.DatabaseProvider
import dev.mongocamp.driver.mongodb.database.MongoConfig
import io.micrometer.core.instrument.binder.MeterBinder
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tag
import org.mongodb.scala.MongoDatabase

case class DatabaseMetrics(mongoDatabase: MongoDatabase, tags: List[Tag] = List.empty) extends MeterBinder {

  override def bindTo(registry: MeterRegistry): Unit = {
    mongoDatabase
      .listCollections()
      .resultList()
      .foreach(
        collection => {
          val metric = CollectionMetrics(mongoDatabase, collection.get("name").get.asString().getValue, tags)
          metric.bindTo(registry)
        }
      )
  }

}

object DatabaseMetrics {
  def apply(mongoDatabaseConfigPath: String, tags: List[Tag]): DatabaseMetrics = {
    val mongoDatabase = DatabaseProvider.fromPath(mongoDatabaseConfigPath).database()
    DatabaseMetrics(mongoDatabase, tags)
  }

  def apply(mongoDatabaseConfigPath: String): DatabaseMetrics = {
    DatabaseMetrics(mongoDatabaseConfigPath, List.empty)
  }

  def apply(tags: List[Tag]): DatabaseMetrics = {
    DatabaseMetrics(MongoConfig.DefaultConfigPathPrefix, tags)
  }

  def apply(): DatabaseMetrics = {
    DatabaseMetrics(List.empty)
  }

}
