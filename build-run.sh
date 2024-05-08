#!/usr/bin/env bash

./mvnw clean package

docker compose up --build -d