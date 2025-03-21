package dev.mongocamp.micrometer.mongodb

import dev.mongocamp.micrometer.mongodb.binder.ServerMetrics
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import io.micrometer.core.instrument.Meter
import scala.jdk.CollectionConverters._

class ServerMetricsSuite extends BaseSuite {

  test("add all metrics for server to registry") {
    val registry = new SimpleMeterRegistry()
    // #region bind-metrics
    ServerMetrics(MongoTestServer.providerMetrics.database()).bindTo(registry)
    // #endregion bind-metrics
    val meters = registry.getMeters.asScala
    assertEquals(meters.size, 1)

    val command = meters.find(_.getId.getName.endsWith(".uptime")).get
    assertEquals(command.getId.getName, "mongodb.server.status.uptime")
    assertEquals(command.getId.getType, Meter.Type.GAUGE)
    assertEquals(command.measure().asScala.size, 1)
    assertEquals(command.measure().asScala.head.getValue > 5000, true)

  }

}
