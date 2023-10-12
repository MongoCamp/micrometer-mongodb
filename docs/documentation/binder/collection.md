# Micrometer Collections Binder
Binder for [Micrometer](https://micrometer.io) to monitor the metrics of MongoDB collection.   

## Metrics
| Metric                                                                    | Type  | Description                                        | Unit    |
|---------------------------------------------------------------------------|-------|----------------------------------------------------|---------|
| mongodb.collection.${mongoDatabase.name}.${collectionName}.size           | Gauge | The total size of all documents for the collection | Byte    |
| mongodb.collection.${mongoDatabase.name}.${collectionName}.count          | Gauge | The total number of documents in the collection    | Objects |
| mongodb.collection.${mongoDatabase.name}.${collectionName}.totalIndexSize | Gauge | The total size of all indexes for the collection   | Byte    |
| mongodb.collection.${mongoDatabase.name}.${collectionName}.avgObjSize     | Gauge | The avg size of documents for the collection       | Byte    |

## Usage

<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/CollectionMetricsSuite.scala#bind-metrics{scala}

