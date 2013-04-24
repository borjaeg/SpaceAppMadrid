#!/bin/bash

git clone https://github.com/kungfoo/geohash-java.git
cd geohash-java
mvn compile
mvn package
mvn install:install-file -Dfile=ch.hsr.geohash-1.0.6.jar -DgroupId=ch.hsr -DartifactId=ch.hsr.geohash -Dversion=1.0.6 -Dpackaging=jar
