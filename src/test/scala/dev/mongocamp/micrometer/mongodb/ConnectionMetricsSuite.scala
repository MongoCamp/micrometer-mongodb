package dev.mongocamp.micrometer.mongodb

import dev.mongocamp.micrometer.mongodb.binder.ConnectionsMetrics
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.simple.SimpleMeterRegistry

import scala.jdk.CollectionConverters._

class ConnectionMetricsSuite extends BaseSuite {

  test("add all metrics for connections to registry") {
    val registry = new SimpleMeterRegistry()
    // #region bind-metrics
    ConnectionsMetrics(MongoTestServer.provider.database()).bindTo(registry)
    // #endregion bind-metrics
    val meters = registry.getMeters.asScala
    assertEquals(meters.size, 5)

    val current = meters.find(_.getId.getName.endsWith(".current")).get
    assertEquals(current.getId.getName, "mongodb.server.status.connections.current")
    assertEquals(current.getId.getType, Meter.Type.GAUGE)
    assertEquals(current.measure().asScala.size, 1)
    assertEquals(current.measure().asScala.head.getValue > 2, true)

    val available = meters.find(_.getId.getName.endsWith(".available")).get
    assertEquals(available.getId.getName, "mongodb.server.status.connections.available")
    assertEquals(available.getId.getType, Meter.Type.GAUGE)
    assertEquals(available.measure().asScala.size, 1)
    assertEquals(available.measure().asScala.head.getValue > 8000, true)

    val totalCreated = meters.find(_.getId.getName.endsWith(".totalCreated")).get
    assertEquals(totalCreated.getId.getName, "mongodb.server.status.connections.totalCreated")
    assertEquals(totalCreated.getId.getType, Meter.Type.GAUGE)
    assertEquals(totalCreated.measure().asScala.size, 1)
    assertEquals(totalCreated.measure().asScala.head.getValue > 5, true)

    val active = meters.find(_.getId.getName.endsWith(".active")).get
    assertEquals(active.getId.getName, "mongodb.server.status.connections.active")
    assertEquals(active.getId.getType, Meter.Type.GAUGE)
    assertEquals(active.measure().asScala.size, 1)
    assertEquals(totalCreated.measure().asScala.head.getValue > 1, true)

    val threaded = meters.find(_.getId.getName.endsWith(".threaded")).get
    assertEquals(threaded.getId.getName, "mongodb.server.status.connections.threaded")
    assertEquals(threaded.getId.getType, Meter.Type.GAUGE)
    assertEquals(threaded.measure().asScala.size, 1)
    assertEquals(totalCreated.measure().asScala.head.getValue > 5, true)

  }

}
