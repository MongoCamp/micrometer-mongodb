# Micrometer Connection Binder

Binder for [Micrometer](https://micrometer.io) to monitor all connection to your MongoDB, not only the connections from your Scala / Java App.

## Metrics

| Metric                                         | Type  | Description                                                                                                       | Unit        |
|------------------------------------------------|-------|-------------------------------------------------------------------------------------------------------------------|-------------|
| mongodb.server.status.connections.current      | Gauge | The number of incoming connections from clients to the database server.                                           | Connections |
| mongodb.server.status.connections.available    | Gauge | The number of unused incoming connections available.                                                              | Connections |
| mongodb.server.status.connections.totalCreated | Gauge | Count of all incoming connections created to the server. This number includes connections that have since closed. | Connections |
| mongodb.server.status.connections.active       | Gauge | The number of active client connections to the server.                                                            | Connections |
| mongodb.server.status.connections.threaded     | Gauge | The number of incoming connections from clients that are assigned to threads.                                     | Connections |

## Usage

<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/ConnectionMetricsSuite.scala#bind-metrics{scala}

