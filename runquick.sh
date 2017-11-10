#!/usr/bin/env bash

mvn -Dmaven.test.skip=true install

docker-compose up --build