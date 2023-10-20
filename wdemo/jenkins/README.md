# WDemo - Jenkins

## Install Jenkins with Helm and Kubernetes

- `helm repo add bitnami https://charts.bitnami.com/bitnami`

- `helm install jenkins-dew -f jenkins-values.yaml bitnami/jenkins`

- `helm status jenkins-dew`

- `helm get notes jenkins-dew`

Other commands:

- `helm uninstall jenkins-dew`

- `helm repo update`

- `helm search repo bitnami`

- `helm list`

## Run Jenkins

Add to hosts file:

`127.0.0.1	jenkins.dew.org`

First get jenkins-password of user `user` from secret.

- `kubectl get secret jenkins-dew --namespace default -o jsonpath='{.data.jenkins-password}'`

The password is base64 encoded.

To decode Base64 encoded text in Powershell:

- `$B64 = 'WDQ2NG9xNW9rMw=='`
- `$DEC = [System.Text.Encoding]::UTF8.GetString([System.Convert]::FromBase64String($B64))`
- `Write-Output $DEC`

To decode Base64 encoded text in Linux:

- `echo WDQ2NG9xNW9rMw== | base64 -d`

## Plugins to run pipeline with Maven / JUnit

Install these plugins:

- `Pipeline`
- `Pipeline: Stage View Plugin`
- `Git`
- `JUnit Plugin`

## Install Maven and Ant on Jenkins Pod (root)

- `apt-get update`

- `apt-get install maven`

- `apt-get install ant`

Now, in Jenkinsfile you can write:

`sh "mvn clean package"`

## Example of Pipeline

See [wcollections](https://github.com/giosil/wcollections) project.

![Jenkins](jenkins.png)

```
pipeline {
    agent any
    
    stages {
        stage('Build') {
           steps {
               git 'https://github.com/giosil/wcollections.git'
               
               // bat "mvn clean package"
               sh "mvn clean package"
           }
           
           post {
               always {
                   junit '**/target/surefire-reports/TEST-*.xml'
               }
           }
        }
    }
}
```