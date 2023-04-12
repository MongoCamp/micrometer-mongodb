package dev.mongocamp.micrometer.mongodb.binder

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.driver.mongodb.database.{DatabaseProvider, MongoConfig}
import io.micrometer.core.instrument._
import io.micrometer.core.instrument.binder.BaseUnits
import org.mongodb.scala.MongoDatabase

import scala.jdk.CollectionConverters.IterableHasAsJava

case class NetworkMetrics(mongoDatabase: MongoDatabase, tags: List[Tag] = List.empty) extends ServerMetricsBase {
  private val namePrefix = "mongodb.server.status.network"

  override def bindTo(registry: MeterRegistry): Unit = {
    Gauge
      .builder(s"$namePrefix.bytesIn", () => getServerStats.getDoubleValue("network.bytesIn"))
      .tags(tags.asJava)
      .description("The total number of bytes that the server has received over network.")
      .baseUnit(BaseUnits.BYTES)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.bytesOut", () => getServerStats.getDoubleValue("network.bytesOut"))
      .tags(tags.asJava)
      .description("The total number of bytes that the server has sent over network.")
      .baseUnit(BaseUnits.BYTES)
      .register(registry)

  }

}

object NetworkMetrics {
  def apply(mongoDatabaseConfigPath: String, tags: List[Tag]): NetworkMetrics = {
    val mongoDatabase = DatabaseProvider.fromPath(mongoDatabaseConfigPath).database()
    NetworkMetrics(mongoDatabase, tags)
  }

  def apply(mongoDatabaseConfigPath: String): NetworkMetrics = {
    NetworkMetrics(mongoDatabaseConfigPath, List.empty)
  }

  def apply(tags: List[Tag]): NetworkMetrics = {
    NetworkMetrics(MongoConfig.DefaultConfigPathPrefix, tags)
  }

  def apply(): NetworkMetrics = {
    NetworkMetrics(List.empty)
  }

}
