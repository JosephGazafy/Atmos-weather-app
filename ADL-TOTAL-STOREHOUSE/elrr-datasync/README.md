# ELRR Datasync
The Datasync component of ELRR is a periodic process which polls data sources (at this time just the [External Services](https://github.com/adlnet/elrr-external-services) proxy) to collect xAPI data and put it into a Kafka Topic for consumption and processing by ELRR.

## Dev Requirements
- [Java JDK 17](https://www.oracle.com/java/technologies/downloads/) or later
- [Maven](https://maven.apache.org/)
- [PostgreSQL Database](https://www.postgresql.org/) (can install, or use [docker container](https://hub.docker.com/_/postgres/tags) version)

## Tools
- Database Client (can use postgres CLI, pgadmin, or a 3rd party client like [DBeaver](https://dbeaver.io/))
- REST Client (such as [Postman](https://www.postman.com/downloads/))
- [Docker](https://www.docker.com/products/docker-desktop/) if working with containers

## Running the Application

### 1. Build the application
`mvn clean install`

This will test, compile, and create a jar for the app in `target/`

### 2. Start and Configure the Database

You will need a running PostgreSQL database containing the schema in `dev-resources/PostgreSQL/schema.sql`.

You will also need to configure the app properties/ENV to point to that database (See **Properties and Environment Variables** below)

One option is to use the ELRR [Local Development Docker Compose](https://github.com/adlnet/elrr-dockercompose) which runs all of the appropriate dependencies with the connection details already in `application-local.properties`, but it does not seed the database with schema, so you will still need to use a DB client to run `schema.sql` against the `sync-db` container's database.

### 3a. Running the application using the Spring Boot Maven plugin: 
This is the recommended and easiest way to run a local version of the application

- `mvn spring-boot:run -D spring-boot.run.profiles=local -e`  (Linux/MacOS)
or
`mvn spring-boot:run -D"spring-boot.run.profiles"=local -e` (Windows)

Note that profile is being set to `local`, this tells spring to leverage `src/main/resources/application-local.properties` which allows you to easily change system settings for your local run. See **Properties and Environment Variables** for details.

### 3b. Running the application using the Jar file
This is closer to how the application will run in a Docker Container or in production.

- `cd target/`
- `java -jar elrrdatasync-_.jar` (you must fill in the version number that matches the current target build)

To configure launch for this method you will set ENV variables instead of tweaking `application-local` as the jar will default to `application.properties` which accepts ENV overrides.

## Properties and Environment Variables
Configuration variables for running the application

| Property | ENV Variable | Default | Description |
| -------- | -------- | -------- | -------- |
| spring.datasource.url (partly, jdbc url)  | PGHOST | - | PostgreSQL Server Host
| spring.datasource.url (partly, jdbc url)  | PGPORT | - | PostgreSQL Server Port
| spring.datasource.url (partly, jdbc url)  | PG_DATABASE | - | PostgreSQL Database Name
| spring.datasource.username  | PG_RW_USER | - | PostgreSQL Username
| spring.datasource.password  | PG_RW_PASSWORD | - | PostgreSQL Password
| spring.jpa.properties.hibernate.default_schema  | PG_SCHEMA | datasync_schema | Default PostgreSQL Schema
| lrsservice.url  | EXTERNAL_SERVICES_URL | http://elrr-external-services | URL of External Services installation
| cronExpression  | RUN_FREQUENCY | `0 0/1 * * * *` | Frequency to run sync process
| purgeCronExpression  | AUDIT_PURGE_FREQUENCY | `0 */60 * * * *` | Frequency to purge cache
| purgeDays  | AUDIT_PURGE_RETENTION | 10 | How many days to retain in audit logs
| brokerUrl (partly, combined with port)   | BROKER_HOST | elrr-kafka | Kafka Broker Host
| brokerUrl (partly, combined with host)  | BROKER_PORT | 9092 | Kafka Broker Port
| kafka.topic  | BROKER_TOPIC | datasync-statements | Kafka Topic
| kafka.dead.letter.topic  | BROKER_DLQ | datasync-statements-dlq | Kafka Dead Letter Queue
| kafka.groupId  | BROKER_GROUPID | elrr-consumer-group | Kafka Group ID
| kafka.partitions  | BROKER_PARTITIONS | 6 | Kafka Partitions Count
| kafka.replicas  | BROKER_REPLICAS | 1 | Kafka Replicas Count

## Running Locally With Dependencies and Testing

### System Dependencies

#### External Services

Datasync will periodically query a running [External Services](https://github.com/adlnet/elrr-external-services) for xAPI updates. You will need to clone and run External Services locally, using the instructions in that repo, before you can get Datasync running successfully.

#### Other Dependencies

Datasync also requires a PostgreSQL database with the appropriate schema, a running kafka, and configuration variables to match both of those resources in order to work. Cloning and running the [ELRR Local Docker Compose](https://github.com/adlnet/elrr-dockercompose) will provide a database and kafka already matching the current defaults in `application-local.properties`. If you start up this compose, and then run using Spring Boot as detailed above, you should have a running version.

### Testing

If you did the prior steps, you should be able to see in logs that every minute the Datasync process queries External Services/LRS for new data, and if there is some it puts the data onto the Kafka topic. If it is not adding anything, make sure the LRS contains data.
