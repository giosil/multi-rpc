# WDemo - Installation of SonarQube

## Install SonarQube with Docker

`docker pull sonarqube:9.9.2-community`

`ddocker run --name sonarqube -p 9000:9000 -d sonarqube:9.9.2-community`

## Install SonarQube with Kubernetes

`kubectl apply -f sonarqube.yaml`

Add to hosts file:

`127.0.0.1	sonarqube.dew.org`

Please wait several minutes before logging in to `http://sonarqube.dew.org`.

## First login

user: admin

passowrd: admin

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
