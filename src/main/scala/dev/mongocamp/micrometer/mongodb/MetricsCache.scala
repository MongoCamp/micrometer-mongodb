package dev.mongocamp.micrometer.mongodb

import com.github.blemale.scaffeine.Cache
import com.github.blemale.scaffeine.Scaffeine
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.util.concurrent.TimeUnit
import org.mongodb.scala.Document
import scala.concurrent.duration._

private[mongodb] object MetricsCache {

  private var expireAfterTime = {
    val confValue = conf.getDuration("dev.mongocamp.micrometer.mongodb.step")
    calculateExpireAfterTime(Duration(confValue.getNano, TimeUnit.NANOSECONDS))
  }

  private var metricsCache: Cache[String, Document] = createCache()

  private def calculateExpireAfterTime(confValue: Duration): FiniteDuration = {
    if (confValue.toSeconds.seconds > 45.seconds) {
      30.seconds
    }
    else {
      var cacheSeconds = confValue.toSeconds.toDouble * .75
      if (cacheSeconds < 1) {
        cacheSeconds = 1
      }
      cacheSeconds.seconds
    }
  }

  private lazy val conf: Config = ConfigFactory.load()

  private def createCache(): Cache[String, Document] = {
    Scaffeine().recordStats().expireAfterWrite(expireAfterTime).build[String, Document]()
  }

  def getMetricsCache: Cache[String, Document] = metricsCache

  def updateCacheTime(value: Duration): Unit = {
    val newExpireAfterTime = calculateExpireAfterTime(value)
    if (newExpireAfterTime < expireAfterTime) {
      expireAfterTime = newExpireAfterTime
      metricsCache = createCache()
    }
  }
}
