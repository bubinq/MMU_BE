@echo off

rem Bring down any existing containers and networks
docker-compose down

rem Build the Docker images
docker-compose build

rem Start the containers in detached mode (background)
docker-compose up -d