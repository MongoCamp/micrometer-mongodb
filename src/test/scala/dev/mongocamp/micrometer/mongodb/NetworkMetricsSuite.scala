package dev.mongocamp.micrometer.mongodb

import dev.mongocamp.micrometer.mongodb.binder.NetworkMetrics
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import io.micrometer.core.instrument.Meter
import scala.jdk.CollectionConverters._

class NetworkMetricsSuite extends BaseSuite {

  test("add all metrics for network to registry") {
    val registry = new SimpleMeterRegistry()
    // #region bind-metrics
    NetworkMetrics(MongoTestServer.providerMetrics.database()).bindTo(registry)
    // #endregion bind-metrics
    val meters = registry.getMeters.asScala
    assertEquals(meters.size, 2)

    val networkBytesIn = meters.find(_.getId.getName.endsWith(".bytesIn")).get
    assertEquals(networkBytesIn.getId.getName, "mongodb.server.status.network.bytesIn")
    assertEquals(networkBytesIn.getId.getType, Meter.Type.GAUGE)
    assertEquals(networkBytesIn.measure().asScala.size, 1)
    assertEquals(networkBytesIn.measure().asScala.head.getValue > 60000, true)

    val bytesOut = meters.find(_.getId.getName.endsWith(".bytesOut")).get
    assertEquals(bytesOut.getId.getName, "mongodb.server.status.network.bytesOut")
    assertEquals(bytesOut.getId.getType, Meter.Type.GAUGE)
    assertEquals(bytesOut.measure().asScala.size, 1)
    assertEquals(bytesOut.measure().asScala.head.getValue > 10, true)

  }

}
