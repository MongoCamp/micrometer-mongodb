# MongoDB Binder
MongoCamp Micrometer MongoDB bases on [MongoCamp MongoDb Driver](https://mongodb-driver.mongocamp.dev/), so you need to connect the MongoDB with the driver by your self or use our implicit [configuration](https://mongodb-driver.mongocamp.dev/documentation/database/config.html) by application.conf.

[Micrometer](https://github.com/micrometer-metrics/micrometer/tree/main/micrometer-core/src/main/java/io/micrometer/core/instrument/binder/mongodb) bring some metrics binder for MongoDb out of the box to monitor the commands and connections from your application. For detail statistics about the state of your MongoDB you can use one of our binder.

* [Collection Binder](collection.md)
* [Connections Binder](connections.md)
* [Database Binder](database.md)
* [Network Binder](network.md)
* [Operation Binder](operation.md)
* [Server Binder](server.md)