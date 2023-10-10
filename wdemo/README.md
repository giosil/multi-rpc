# WDemo

A simple frontend backend application using multi-rpc library.

## Build

Build frontend (wdemo-fe):

- `cd wdemo-fe`
- `mvn clean package`
- `docker build -t wdemo-fe .`

Build backend (wdemo-be):

- `cd wdemo-be`
- `mvn clean package`
- `docker build -t wdemo-be .`

## Run with Kubernetes

- `cd k8s`
- `kubectl apply -f wdemo.yaml`

Add to hosts file:

127.0.0.1	wdemo.dew.org

Launch:

- `http://wdemo.dew.org/wdemo-fe`

## Publish on WSO2

- `cd wso2`
- `kubectl apply -f wso2.yaml`

Expose these ports:

- `kubectl port-forward deployment/wso2 8280:8280` - API Manager (HTTP)
- `kubectl port-forward deployment/wso2 8243:8243` - API Manager (HTTPS)
- `kubectl port-forward deployment/wso2 9443:9443` - Publisher / Carbon / DevPortal

Add to hosts file:

127.0.0.1	gwapi.dew.org

Launch:

- `https://localhost:9443/publisher`

Log in with admin / admin and import API from file `jsonrpc-api-docs.yaml`.

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
