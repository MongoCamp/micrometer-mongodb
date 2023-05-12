package dev.mongocamp.micrometer.mongodb

import dev.mongocamp.driver.mongodb._
import dev.mongocamp.micrometer.mongodb.registry.MongoStepMeterRegistry
import io.micrometer.core.instrument.binder.jvm.{JvmGcMetrics, JvmMemoryMetrics}

import java.util.Date
import scala.concurrent.duration.DurationInt

class RegistrySuite extends BaseSuite {

  test("check and push information to mongodb from configuration") {
    val startDate = new Date()
    // #region registry-with-config-string
    val collectionName = "test_registry_collection_with_wait"
    val registry = MongoStepMeterRegistry(collectionName, "unit.test.mongo")
    // #endregion registry-with-config-string
    new JvmGcMetrics().bindTo(registry)
    new JvmMemoryMetrics().bindTo(registry)
    Thread.sleep(11.seconds.toMillis)
    val dao = MongoTestServer.provider.dao(collectionName)
    val count = dao.count().result()
    assertEquals(count >= 2, true)
    val headDocument = dao.find().result()
    val testDate = headDocument.getDateValue("date")
    val endDate = new Date()
    assertEquals(!(testDate.before(startDate) || testDate.after(endDate)), true)
    val jvmGcMaxData = headDocument.getValueOption("jvm_gc_max_data_size")
    assertEquals(jvmGcMaxData.isDefined, true)
  }

  test("check and push information to mongodb with individual Map Config") {
    val startDate = new Date()
    val collectionName = "test_registry_collection_with_wait_15"
    val registry = MongoStepMeterRegistry(collectionName, "unit.test.mongo", Map("step" -> "15s"))
    new JvmGcMetrics().bindTo(registry)
    new JvmMemoryMetrics().bindTo(registry)
    Thread.sleep(31.seconds.toMillis)
    registry.getMeters
    val dao = MongoTestServer.provider.dao(collectionName)
    val count = dao.count().result()
    assertEquals(count >= 1, true)
    val headDocument = dao.find().result()
    val testDate = headDocument.getDateValue("date")
    val endDate = new Date()
    assertEquals(!(testDate.before(startDate) || testDate.after(endDate)), true)
    val jvmGcMaxData = headDocument.getValueOption("jvm_gc_max_data_size")
    assertEquals(jvmGcMaxData.isDefined, true)
  }


  test("check and push information to mongodb with dao") {
    val startDate = new Date()
    // #region registry-with-dao
    val collectionName = "test_registry_collection_with_wait_from_dao"
    val dao = MongoTestServer.provider.dao(collectionName)
    val registry = MongoStepMeterRegistry(dao)
    // #endregion registry-with-dao
    new JvmGcMetrics().bindTo(registry)
    new JvmMemoryMetrics().bindTo(registry)
    Thread.sleep(11.seconds.toMillis)
    val count = dao.count().result()
    assertEquals(count >= 2, true)
    val headDocument = dao.find().result()
    val testDate = headDocument.getDateValue("date")
    val endDate = new Date()
    assertEquals(!(testDate.before(startDate) || testDate.after(endDate)), true)
    val jvmGcMaxData = headDocument.getValueOption("jvm_gc_max_data_size")
    assertEquals(jvmGcMaxData.isDefined, true)
  }


  test("check and push information to mongodb collectionName and with individual Map Config") {
    val startDate = new Date()
    // #region registry-with-overridden-config
    val collectionName = "test_registry_collection_with_wait_from_dao_15"
    val configurationMap = Map("step" -> "15s", "save" -> "2m")
    val registry = MongoStepMeterRegistry(collectionName, configurationMap)
    // #endregion registry-with-overridden-config
    new JvmGcMetrics().bindTo(registry)
    new JvmMemoryMetrics().bindTo(registry)
    Thread.sleep(31.seconds.toMillis)
    registry.getMeters
    val dao = MongoTestServer.provider.dao(collectionName)
    val count = dao.count().result()
    assertEquals(count >= 1, true)
    val headDocument = dao.find().result()
    val testDate = headDocument.getDateValue("date")
    val endDate = new Date()
    assertEquals(!(testDate.before(startDate) || testDate.after(endDate)), true)
    val jvmGcMaxData = headDocument.getValueOption("jvm_gc_max_data_size")
    assertEquals(jvmGcMaxData.isDefined, true)
  }
}
