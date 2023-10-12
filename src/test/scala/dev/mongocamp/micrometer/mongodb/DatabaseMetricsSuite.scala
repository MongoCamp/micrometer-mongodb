package dev.mongocamp.micrometer.mongodb

import dev.mongocamp.micrometer.mongodb.binder.DatabaseMetrics
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.simple.SimpleMeterRegistry

import scala.jdk.CollectionConverters._

class DatabaseMetricsSuite extends BaseSuite {

  test("add all metrics for database to registry") {
    val registry = new SimpleMeterRegistry()
    // #region bind-metrics
    DatabaseMetrics(MongoTestServer.providerMetrics.database()).bindTo(registry)
    // #endregion bind-metrics
    val meters = registry.getMeters.asScala
    assertEquals(meters.size, 8)

    val size = meters.find(_.getId.getName.endsWith("mongocamp-unit-test-metrics.pokedex.size")).get
    assertEquals(size.getId.getName, "mongodb.collection.mongocamp-unit-test-metrics.pokedex.size")
    assertEquals(size.getId.getType, Meter.Type.GAUGE)
    assertEquals(size.measure().asScala.size, 1)
    assertEquals(size.measure().asScala.head.getValue > 60000, true)

    val totalIndexSize = meters.find(_.getId.getName.endsWith("mongocamp-unit-test-metrics.pokedex.totalIndexSize")).get
    assertEquals(totalIndexSize.getId.getName, "mongodb.collection.mongocamp-unit-test-metrics.pokedex.totalIndexSize")
    assertEquals(totalIndexSize.getId.getType, Meter.Type.GAUGE)
    assertEquals(totalIndexSize.measure().asScala.size, 1)
    assertEquals(totalIndexSize.measure().asScala.head.getValue > 4000, true)

    val avgObjSize = meters.find(_.getId.getName.endsWith("mongocamp-unit-test-metrics.pokedex.avgObjSize")).get
    assertEquals(avgObjSize.getId.getName, "mongodb.collection.mongocamp-unit-test-metrics.pokedex.avgObjSize")
    assertEquals(avgObjSize.getId.getType, Meter.Type.GAUGE)
    assertEquals(avgObjSize.measure().asScala.size, 1)
    assertEquals(avgObjSize.measure().asScala.head.getValue > 400, true)

    val count = meters.find(_.getId.getName.endsWith("mongocamp-unit-test-metrics.pokedex.count")).get
    assertEquals(count.getId.getName, "mongodb.collection.mongocamp-unit-test-metrics.pokedex.count")
    assertEquals(count.getId.getType, Meter.Type.GAUGE)
    assertEquals(count.measure().asScala.size, 1)
    assertEquals(count.measure().asScala.head.getValue, 151.0)

  }

}
