#!/bin/bash

if [ ! -f ".env" ]; then
    cp .env.example .env
fi

java -jar spring-boot-docker-1.0.jar