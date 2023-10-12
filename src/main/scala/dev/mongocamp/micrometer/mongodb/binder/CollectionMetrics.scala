package dev.mongocamp.micrometer.mongodb.binder

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.driver.mongodb.database.{DatabaseProvider, MongoConfig}
import dev.mongocamp.micrometer.mongodb.MetricsCache
import io.micrometer.core.instrument.binder.{BaseUnits, MeterBinder}
import io.micrometer.core.instrument.{Gauge, MeterRegistry, Tag}
import org.mongodb.scala.{Document, MongoDatabase}

import scala.jdk.CollectionConverters.IterableHasAsJava

case class CollectionMetrics(mongoDatabase: MongoDatabase, collectionName: String, tags: List[Tag] = List.empty) extends MeterBinder {
  private val namePrefix = s"mongodb.collection.${mongoDatabase.name}.${collectionName}"

  override def bindTo(registry: MeterRegistry): Unit = {
    Gauge
      .builder(s"$namePrefix.size", () => getCollectionStats.getDoubleValue("size"))
      .tags(tags.asJava)
      .description(s"The total size of all documents for the collection ${collectionName}.")
      .baseUnit(BaseUnits.BYTES)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.totalIndexSize", () => getCollectionStats.getDoubleValue("totalIndexSize"))
      .tags(tags.asJava)
      .description(s"The total size of all indexes for the collection ${collectionName}.")
      .baseUnit(BaseUnits.BYTES)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.avgObjSize", () => getCollectionStats.getDoubleValue("avgObjSize"))
      .tags(tags.asJava)
      .description(s"The avg size of documents for the collection ${collectionName}.")
      .baseUnit(BaseUnits.BYTES)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.count", () => getCollectionStats.getDoubleValue("count"))
      .tags(tags.asJava)
      .description(s"The total number of documents in the collection ${collectionName} to the return document.")
      .baseUnit(BaseUnits.OBJECTS)
      .register(registry)
  }

  private def getCollectionStats: Document = {
    val cacheKey       = s"${mongoDatabase.name}:::${collectionName}"
    val cachedDocument = MetricsCache.getMetricsCache.getIfPresent(cacheKey)
    cachedDocument.getOrElse({
      val freshDocument = refreshCollectionStatsFromDatabase
      MetricsCache.getMetricsCache.put(cacheKey, freshDocument)
      freshDocument
    })
  }

  private def refreshCollectionStatsFromDatabase: Document = {
    mongoDatabase
      .runCommand(Map("collStats" -> collectionName))
      .result()
  }

}

object CollectionMetrics {
  def apply(collectionName: String, mongoDatabaseConfigPath: String, tags: List[Tag]): CollectionMetrics = {
    val mongoDatabase = DatabaseProvider.fromPath(mongoDatabaseConfigPath).database()
    CollectionMetrics(mongoDatabase, collectionName, tags)
  }

  def apply(collectionName: String, mongoDatabaseConfigPath: String): CollectionMetrics = {
    CollectionMetrics(collectionName, mongoDatabaseConfigPath, List.empty)
  }

  def apply(collectionName: String): CollectionMetrics = {
    CollectionMetrics(collectionName, List.empty)
  }
  def apply(collectionName: String, tags: List[Tag]): CollectionMetrics = {
    CollectionMetrics(collectionName, MongoConfig.DefaultConfigPathPrefix, tags)
  }

}
