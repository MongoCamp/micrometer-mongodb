# Micrometer Server Binder

Binder for [Micrometer](https://micrometer.io) some server status metrics of your MongoDB.

## Metrics

| Metric                       | Type  | Description                          | Unit         |
|------------------------------|-------|--------------------------------------|--------------|
| mongodb.server.status.uptime | Gauge | The uptime of your Server in Seconds | Milliseconds |

## Usage

<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/ServerMetricsSuite.scala#bind-metrics{scala}
