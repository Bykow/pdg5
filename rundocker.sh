#!/usr/bin/env bash

docker-compose down -v
docker-compose rm -vf
docker-compose up --build