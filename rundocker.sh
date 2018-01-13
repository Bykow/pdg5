#!/usr/bin/env bash

# Use this script for COMPLETE install

mvn clean install

docker-compose down
docker-compose up --build --no-start
docker-compose up