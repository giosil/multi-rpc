# Multi-RPC - WDemo - Frontend

## Build

- `mvn clean package`

## Build Docker Image

- `docker build -t wdemo-fe .`

or

- `docker build -t wdemo-fe:1.0.0 .`
- `docker tag wdemo-fe:1.0.0 wdemo-fe:latest`

## Run Docker Image

- `docker network create wdemo-net`
- `docker run --name wdemo-fe-con -p 8080:8080 --network wdemo-net -d wdemo-fe`
- `docker exec -it wdemo-fe-con /bin/bash`
- `docker stop wdemo-fe-con` - To stop the running container
- `docker rm wdemo-fe-con` - To remove the container

## Remove Docker Image

- `docker rmi wdemo-fe`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
