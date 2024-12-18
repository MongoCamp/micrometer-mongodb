## [v1.0.1] - 2024-10-27
### :bug: Bug Fixes
- [`f3e2ef0`](https://github.com/MongoCamp/micrometer-mongodb/commit/f3e2ef04f845af17658861411890922139c2d596) - mongodb-driver 5.1.4 not longer support method `listCollectionNames()` *(commit by [@QuadStingray](https://github.com/QuadStingray))*
- [`2d95f52`](https://github.com/MongoCamp/micrometer-mongodb/commit/2d95f52b3926e0589f1a7caa2d309b33f4aba2b3) - mongodb-driver 5.1.4 not longer support method `listCollectionNames()` *(commit by [@QuadStingray](https://github.com/QuadStingray))*


## [v1.0.0] - 2024-10-26
### :boom: BREAKING CHANGES
- due to [`6b8ae17`](https://github.com/MongoCamp/micrometer-mongodb/commit/6b8ae174016edcdf6dde6cf56146f9bba9d7a1ec) - Drop Java 11 and 19 Support *(commit by [@QuadStingray](https://github.com/QuadStingray))*:

  Only support Java 21+ now


### :recycle: Refactors
- [`6b8ae17`](https://github.com/MongoCamp/micrometer-mongodb/commit/6b8ae174016edcdf6dde6cf56146f9bba9d7a1ec) - **jvm**: Drop Java 11 and 19 Support *(commit by [@QuadStingray](https://github.com/QuadStingray))*

### :wrench: Chores
- [`727ced6`](https://github.com/MongoCamp/micrometer-mongodb/commit/727ced6995d449e78bc343dba68744573cc325b2) - 12 dependency updates for micrometer-mongodb *(commit by [@QuadStingray](https://github.com/QuadStingray))*


## [0.6.3]() (2023-10-12)

## [0.6.2]() (2023-10-12)


### Code Refactoring

* Replace Akka with Pekko ([364f739](https://github.com/MongoCamp/micrometer-mongodb/commit/364f739b8fbb17a339370469694f5dcffb42da95))

### Maintenance

* **dependenies:** Update for many Dependencies ([cbe9f93](https://github.com/MongoCamp/micrometer-mongodb/commit/cbe9f93aa509300f10f0e1ed865808030cd53676))
## [0.6.1]() (2023-05-13)


### Bug Fixes

* ignore saves when only date is in save map ([2d83fb2](https://github.com/MongoCamp/micrometer-mongodb/commit/2d83fb2d1cc939d6e5c606b804f9f8cbf94a20ba))
## [0.6.0]() (2023-04-12)


### Features

* apply methods for default values of registry and binder ([ddf00df](https://github.com/MongoCamp/micrometer-mongodb/commit/ddf00df3bee48c6632db96b80117eed5fa932b79))
## [0.5.0]() (2023-04-06)


### Features

* add missing dependencies ([737f92e](https://github.com/MongoCamp/micrometer-mongodb/commit/737f92e5cf77964623d7102544e2d4e33c7ecc4f))* extract from mongocamp-server to own package ([cc1324c](https://github.com/MongoCamp/micrometer-mongodb/commit/cc1324c9ac5ddb414127d1515a6ed8dfad907ecd))* removed 2.12 support ([2596bd6](https://github.com/MongoCamp/micrometer-mongodb/commit/2596bd641315e3c134e938cbe0bb3744e57ab884))
[v1.0.0]: https://github.com/MongoCamp/micrometer-mongodb/compare/v0.7.1...v1.0.0
[v1.0.1]: https://github.com/MongoCamp/micrometer-mongodb/compare/v1.0.0...v1.0.1
