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

- `$B64 ='WDVCd0gwZ0tNTQ=='`
- `$DEC = [System.Text.Encoding]::UTF8.GetString([System.Convert]::FromBase64String($B64))`
- `Write-Output $DEC`

To decode Base64 encoded text in Linux:

- `echo WDVCd0gwZ0tNTQ== | base64 -d`