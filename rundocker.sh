#!/usr/bin/env bash

mvn clean install

docker-compose down -v
docker-compose up --build