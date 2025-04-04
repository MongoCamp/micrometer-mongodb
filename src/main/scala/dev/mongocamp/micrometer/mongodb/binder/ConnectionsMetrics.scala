package dev.mongocamp.micrometer.mongodb.binder

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.driver.mongodb.database.DatabaseProvider
import dev.mongocamp.driver.mongodb.database.MongoConfig
import io.micrometer.core.instrument._
import io.micrometer.core.instrument.binder.BaseUnits
import org.mongodb.scala.MongoDatabase
import scala.jdk.CollectionConverters.IterableHasAsJava

case class ConnectionsMetrics(mongoDatabase: MongoDatabase, tags: List[Tag] = List.empty) extends ServerMetricsBase {
  private val namePrefix = s"mongodb.server.status.connections"

  override def bindTo(registry: MeterRegistry): Unit = {

    Gauge
      .builder(s"$namePrefix.current", () => getServerStats.getDoubleValue("connections.current"))
      .tags(tags.asJava)
      .description("The number of incoming connections from clients to the database server.")
      .baseUnit(BaseUnits.CONNECTIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.available", () => getServerStats.getDoubleValue("connections.available"))
      .tags(tags.asJava)
      .description("The number of unused incoming connections available.")
      .baseUnit(BaseUnits.CONNECTIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.totalCreated", () => getServerStats.getDoubleValue("connections.totalCreated"))
      .tags(tags.asJava)
      .description("Count of all incoming connections created to the server. This number includes connections that have since closed.")
      .baseUnit(BaseUnits.CONNECTIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.active", () => getServerStats.getDoubleValue("connections.active"))
      .tags(tags.asJava)
      .description("The number of active client connections to the server.")
      .baseUnit(BaseUnits.CONNECTIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.threaded", () => getServerStats.getDoubleValue("connections.threaded"))
      .tags(tags.asJava)
      .description("The number of incoming connections from clients that are assigned to threads.")
      .baseUnit(BaseUnits.CONNECTIONS)
      .register(registry)

  }

}

object ConnectionsMetrics {

  def apply(mongoDatabaseConfigPath: String, tags: List[Tag]): ConnectionsMetrics = {
    val mongoDatabase = DatabaseProvider.fromPath(mongoDatabaseConfigPath).database()
    ConnectionsMetrics(mongoDatabase, tags)
  }

  def apply(mongoDatabaseConfigPath: String): ConnectionsMetrics = {
    ConnectionsMetrics(mongoDatabaseConfigPath, List.empty)
  }

  def apply(tags: List[Tag]): ConnectionsMetrics = {
    ConnectionsMetrics(MongoConfig.DefaultConfigPathPrefix, tags)
  }

  def apply(): ConnectionsMetrics = {
    ConnectionsMetrics(List.empty)
  }

}
