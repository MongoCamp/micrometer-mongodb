# Micrometer Network Binder
Binder for [Micrometer](https://micrometer.io) some network metrics of your MongoDB.

## Metrics
| Metric                                 | Type  | Description                                                          | Unit  |
|----------------------------------------|-------|----------------------------------------------------------------------|-------|
| mongodb.server.status.network.bytesIn  | Gauge | The total number of bytes that the server has received over network. | Bytes |
| mongodb.server.status.network.bytesOut | Gauge | The total number of bytes that the server has sent over network      | Bytes |

## Usage

<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/NetworkMetricsSuite.scala#bind-metrics{scala}
