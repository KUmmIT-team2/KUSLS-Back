#!/bin/bash

# 최신 이미지 pull
docker pull kroad0129/kusls:latest

# docker-compose 실행
docker compose down
docker compose up -d
