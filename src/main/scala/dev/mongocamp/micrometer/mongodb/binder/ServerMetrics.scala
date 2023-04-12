package dev.mongocamp.micrometer.mongodb.binder

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.driver.mongodb.database.{DatabaseProvider, MongoConfig}
import io.micrometer.core.instrument._
import io.micrometer.core.instrument.binder.BaseUnits
import org.mongodb.scala.MongoDatabase

import scala.jdk.CollectionConverters.IterableHasAsJava

case class ServerMetrics(mongoDatabase: MongoDatabase, tags: List[Tag] = List.empty) extends ServerMetricsBase {
  private val namePrefix = "mongodb.server.status"

  override def bindTo(registry: MeterRegistry): Unit = {

    Gauge
      .builder(s"$namePrefix.uptime", () => getServerStats.getDoubleValue("uptimeMillis"))
      .tags(tags.asJava)
      .description("The uptime of your Server in Seconds")
      .baseUnit(BaseUnits.MILLISECONDS)
      .register(registry)

  }

}

object ServerMetrics {
  def apply(mongoDatabaseConfigPath: String, tags: List[Tag]): ServerMetrics = {
    val mongoDatabase = DatabaseProvider.fromPath(mongoDatabaseConfigPath).database()
    ServerMetrics(mongoDatabase, tags)
  }

  def apply(mongoDatabaseConfigPath: String): ServerMetrics = {
    ServerMetrics(mongoDatabaseConfigPath, List.empty)
  }

  def apply(tags: List[Tag]): ServerMetrics = {
    ServerMetrics(MongoConfig.DefaultConfigPathPrefix, tags)
  }

  def apply(): ServerMetrics = {
    ServerMetrics(List.empty)
  }

}