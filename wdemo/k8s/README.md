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

## Run

Add to hosts file:

127.0.0.1	wdemo.dew.org

Launch:

- `http://wdemo.dew.org/wdemo-fe`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
