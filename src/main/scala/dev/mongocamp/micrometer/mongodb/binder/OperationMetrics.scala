package dev.mongocamp.micrometer.mongodb.binder

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.driver.mongodb.database.{ DatabaseProvider, MongoConfig }
import io.micrometer.core.instrument._
import io.micrometer.core.instrument.binder.BaseUnits
import org.mongodb.scala.MongoDatabase

import scala.jdk.CollectionConverters.IterableHasAsJava

case class OperationMetrics(mongoDatabase: MongoDatabase, tags: List[Tag] = List.empty) extends ServerMetricsBase {
  private val namePrefix = "mongodb.server.status.operations"

  override def bindTo(registry: MeterRegistry): Unit = {
    Gauge
      .builder(s"$namePrefix.insert", () => getServerStats.getDoubleValue("opcounters.insert"))
      .tags(tags.asJava)
      .description("The total number of insert operations received since the mongod instance last started.")
      .baseUnit(BaseUnits.OPERATIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.query", () => getServerStats.getDoubleValue("opcounters.query"))
      .tags(tags.asJava)
      .description("The total number of queries received since the mongod instance last started.")
      .baseUnit(BaseUnits.OPERATIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.update", () => getServerStats.getDoubleValue("opcounters.update"))
      .tags(tags.asJava)
      .description("The total number of update operations received since the mongod instance last started.")
      .baseUnit(BaseUnits.OPERATIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.delete", () => getServerStats.getDoubleValue("opcounters.delete"))
      .tags(tags.asJava)
      .description("The total number of delete operations since the mongod instance last started.")
      .baseUnit(BaseUnits.OPERATIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.getmore", () => getServerStats.getDoubleValue("opcounters.getmore"))
      .tags(tags.asJava)
      .description("The total number of getMore operations since the mongod instance last started.")
      .baseUnit(BaseUnits.OPERATIONS)
      .register(registry)

    Gauge
      .builder(s"$namePrefix.command", () => getServerStats.getDoubleValue("opcounters.command"))
      .tags(tags.asJava)
      .description("The total number of commands issued to the database since the mongod instance last started.")
      .baseUnit(BaseUnits.OPERATIONS)
      .register(registry)
  }

}

object OperationMetrics {
  def apply(mongoDatabaseConfigPath: String, tags: List[Tag]): OperationMetrics = {
    val mongoDatabase = DatabaseProvider.fromPath(mongoDatabaseConfigPath).database()
    OperationMetrics(mongoDatabase, tags)
  }

  def apply(mongoDatabaseConfigPath: String): OperationMetrics = {
    OperationMetrics(mongoDatabaseConfigPath, List.empty)
  }

  def apply(tags: List[Tag]): OperationMetrics = {
    OperationMetrics(MongoConfig.DefaultConfigPathPrefix, tags)
  }

  def apply(): OperationMetrics = {
    OperationMetrics(List.empty)
  }

}
