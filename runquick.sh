#!/usr/bin/env bash

# Use this script for quick lauch (do not recreate the DB etc)

mvn -Dmaven.test.skip=true install

docker-compose up --build