#!/bin/bash

# DockerHub 로그인 (Private repo라면)
docker login -u kroad0129 -p yourpassword

# 최신 이미지 pull
docker pull kroad0129/kusls:latest

# docker-compose 실행
docker compose down
docker compose up -d
