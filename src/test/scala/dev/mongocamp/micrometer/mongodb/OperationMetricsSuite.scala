package dev.mongocamp.micrometer.mongodb

import dev.mongocamp.micrometer.mongodb.binder.OperationMetrics
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import io.micrometer.core.instrument.Meter
import scala.jdk.CollectionConverters._

class OperationMetricsSuite extends BaseSuite {

  test("add all metrics for operation to registry") {
    val registry = new SimpleMeterRegistry()
    // #region bind-metrics
    OperationMetrics(MongoTestServer.providerMetrics.database()).bindTo(registry)
    // #endregion bind-metrics
    val meters = registry.getMeters.asScala
    assertEquals(meters.size, 6)

    val command = meters.find(_.getId.getName.endsWith(".command")).get
    assertEquals(command.getId.getName, "mongodb.server.status.operations.command")
    assertEquals(command.getId.getType, Meter.Type.GAUGE)
    assertEquals(command.measure().asScala.size, 1)
    assertEquals(command.measure().asScala.head.getValue > 5, true)

    val getmore = meters.find(_.getId.getName.endsWith(".getmore")).get
    assertEquals(getmore.getId.getName, "mongodb.server.status.operations.getmore")
    assertEquals(getmore.getId.getType, Meter.Type.GAUGE)
    assertEquals(getmore.measure().asScala.size, 1)
    assertEquals(getmore.measure().asScala.head.getValue, 0.0)

    val insert = meters.find(_.getId.getName.endsWith(".insert")).get
    assertEquals(insert.getId.getName, "mongodb.server.status.operations.insert")
    assertEquals(insert.getId.getType, Meter.Type.GAUGE)
    assertEquals(insert.measure().asScala.size, 1)
    assertEquals(insert.measure().asScala.head.getValue > 400, true)

    val update = meters.find(_.getId.getName.endsWith(".update")).get
    assertEquals(update.getId.getName, "mongodb.server.status.operations.update")
    assertEquals(update.getId.getType, Meter.Type.GAUGE)
    assertEquals(update.measure().asScala.size, 1)
    assertEquals(update.measure().asScala.head.getValue, 0.0)

    val query = meters.find(_.getId.getName.endsWith(".query")).get
    assertEquals(query.getId.getName, "mongodb.server.status.operations.query")
    assertEquals(query.getId.getType, Meter.Type.GAUGE)
    assertEquals(query.measure().asScala.size, 1)
    assertEquals(query.measure().asScala.head.getValue >= 0.0, true)

    val delete = meters.find(_.getId.getName.endsWith(".delete")).get
    assertEquals(delete.getId.getName, "mongodb.server.status.operations.delete")
    assertEquals(delete.getId.getType, Meter.Type.GAUGE)
    assertEquals(delete.measure().asScala.size, 1)
    assertEquals(delete.measure().asScala.head.getValue, 0.0)

  }

}
