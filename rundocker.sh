#!/usr/bin/env bash

# Use this script for COMPLETE install

mvn clean install

docker-compose down -v
docker-compose up --build