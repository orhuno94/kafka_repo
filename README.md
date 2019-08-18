Welcome file
Welcome file
# kafka_repo
 
This repo contains 3 different projects;

1) Consumer;
Consumes kafka messages on default settings (zookeeper on p:2181 and kafka on p:9092)
posts consumed messages on websocket and serves it on localhost:8080
then writes data to mongoDB on default settings
provides rest API on localhost:8080/GetMessages and can be queried via UI on localhost:8080.

2) Producer;
Reads files on given path then writes it to kafka on default settings.

3) Generator;
Generates log files for testing.
 
#### Dependencies
* maven,
* zookeeper (install and run with default configuration),
* kafka (install and run with default configuration),
* mongodb (install and run with default configuration)

----
Running application;
`$ docker run -t -i -p 8080:8080 orhunozogul94/teb_kafka`
> The application can be reached from browser on port 8080.
kafka_repo
This repo contains 3 different projects;

Consumer;
Consumes kafka messages on default settings (zookeeper on p:2181 and kafka on p:9092)
posts consumed messages on websocket and serves it on localhost:8080
then writes data to mongoDB on default settings
provides rest API on localhost:8080/GetMessages and can be queried via UI on localhost:8080.

Producer;
Reads files on given path then writes it to kafka on default settings.

Generator;
Generates log files for testing.

Dependencies
maven,
zookeeper (install and run with default configuration),
kafka (install and run with default configuration),
mongodb (install and run with default configuration)
Running application;
$ docker run -t -i -p 8080:8080 orhunozogul94/teb_kafka

The application can be reached from browser on port 8080.

Markdown 840 bytes 126 words 26 lines Ln 26, Col 0 HTML 684 characters 115 words 19 paragraphs