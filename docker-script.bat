@echo off

rem Change to the directory where your docker-compose.yml file is located
cd /d "C:\Users\ShittyRestaurant\eclipse-workspace\back-ends"

rem Bring down any existing containers and networks
docker-compose down

rem Build the Docker images
docker-compose build

rem Start the containers in detached mode (background)
docker-compose up -d