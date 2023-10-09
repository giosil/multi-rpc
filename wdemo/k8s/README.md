# WDemo

A simple frontend backend application.

## Build

Build Backend

- `cd wdemo-be`
- `mvn clean package`
- `docker build -t wdemo-be .`
- `docker tag wdemo-be:1.0.0 wdemo-be:latest`

Build Frontend

- `cd wdemo-fe`
- `mvn clean package`
- `docker build -t wdemo-fe:1.0.0 .`
- `docker tag wdemo-fe:1.0.0 wdemo-fe:latest`

Create Kubernetes application

- `kubectl apply -f wdemo.yaml`

Delete Kubernetes application

- `kubectl delete -f wdemo.yaml`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
