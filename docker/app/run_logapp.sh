#!/bin/bash
java -jar /app/consumer-0.0.1-SNAPSHOT.jar &
java -jar /app/kafka-0.0.1-SNAPSHOT-jar-with-dependencies.jar /app/logs inbound &
java Regex /app/logs/log.txt
