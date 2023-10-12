# Micrometer Operations Binder

Binder for [Micrometer](https://micrometer.io) some network metrics of your MongoDB.

## Metrics

| Metric                                   | Type  | Description                                                                                 | Unit       |
|------------------------------------------|-------|---------------------------------------------------------------------------------------------|------------|
| mongodb.server.status.operations.insert  | Gauge | The total number of insert operations received since the mongod instance last started.      | Operations |
| mongodb.server.status.operations.query   | Gauge | The total number of queries received since the mongod instance last started.                | Operations |
| mongodb.server.status.operations.update  | Gauge | The total number of update operations received since the mongod instance last started.      | Operations |
| mongodb.server.status.operations.delete  | Gauge | The total number of delete operations since the mongod instance last started.               | Operations |
| mongodb.server.status.operations.getmore | Gauge | The total number of getMore operations since the mongod instance last started.              | Operations |
| mongodb.server.status.operations.command | Gauge | The total number of commands issued to the database since the mongod instance last started. | Operations |

## Usage

<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/OperationMetricsSuite.scala#bind-metrics{scala}
