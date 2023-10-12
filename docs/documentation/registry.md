# Micrometer Registry
Micrometer MongoDb is based on [MongoCamp MongoDb Driver](https://mongodb-driver.mongocamp.dev/) so the [configuration](https://mongodb-driver.mongocamp.dev/documentation/database/config.html) for connections without given DAO are loaded like MongoCamp Driver is loading [default connection](https://mongodb-driver.mongocamp.dev/documentation/database/config.html).

Configurations for Micrometer MongoDb registry is loaded from application.conf (or reference.conf) path `dev.mongocamp.micrometer.mongodb`. For save max duration the setting `save` is used.

## Registry with config
<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/RegistrySuite.scala#registry-with-config-string{scala}

## Registry with DAO
<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/RegistrySuite.scala#registry-with-dao{scala}

## Registry with overridden config
Every apply-methode for `MongoStepMeterRegistry` has the possibility to override the settings from application.conf with an Map[String, String], for example if you have multiple registries in running application with different settings.

<<< @/../src/test/scala/dev/mongocamp/micrometer/mongodb/RegistrySuite.scala#registry-with-overridden-config{scala}

The reference.conf default values are:

<<< @/../src/main/resources/reference.conf{json}