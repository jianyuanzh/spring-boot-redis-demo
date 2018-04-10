#!/usr/bin/env bash

docker stop spring-boot-demo-redis
docker rm spring-boot-demo-redis

docker run -idt -p 6379:6379 -v `pwd`/data:/data --name spring-boot-demo-redis -v `pwd`/redis.conf:/etc/redis/redis_default.conf redis:3.2