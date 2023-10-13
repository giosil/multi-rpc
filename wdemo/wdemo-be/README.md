# Multi-RPC - WDemo - Backend

## Build

- `mvn clean package`

## Build Docker Image

- `docker build -t wdemo-be .`

or

- `docker build -t wdemo-be:1.0.0 .`
- `docker tag wdemo-be:1.0.0 wdemo-be:latest`

## Run Docker Image

- `docker network create wdemo-net`
- `docker run --name wdemo-be-con -p 8081:8080 --network wdemo-net -d wdemo-be`
- `docker exec -it wdemo-be-con /bin/bash`
- `docker stop wdemo-be-con` - To stop the running container
- `docker rm wdemo-be-con` - To remove the container

## Remove Docker Image

- `docker rmi wdemo-be`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
