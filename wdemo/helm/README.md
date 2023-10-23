# WDemo - Deploy application with Helm

## Install application

In this folder named `helm`:

- `helm install wdemo-be ./wdemo-be` - this install `wdemo-be` application from project folder
- `helm install wdemo-fe ./wdemo-fe` - this install `wdemo-fe` application from project folder

- `helm list` - this show all releases
- `helm get all wdemo-be` - this show all info of `wdemo-be` application
- `helm get all wdemo-fe` - this show all info of `wdemo-fe` application

- `helm uninstall wdemo-be` - this uninstall `wdemo-be` application
- `helm uninstall wdemo-fe` - this uninstall `wdemo-fe` application

Other commands:

- `helm create wdemo-be` - this will create `wdemo-be` project folder
- `helm lint wdemo-be` - this will check `wdemo-be` project folder
- `helm template wdemo-be ./wdemo-be` - this render chart templates locally and display the output
- `helm show all ./wdemo-be` - this will show all information of the chart
- `helm status wdemo-be` - this will display the status of the named release
- `helm history wdemo-be` - this will prints historical revisions for a given release
- `helm package wdemo-be` - this will create package from `wdemo-be` project folder (`wdemo-be-0.1.0.tgz`)
- `helm install wdemo-be ./wdemo-be-0.1.0.tgz` - this install `wdemo-be` application from package
- `helm upgrade wdemo-be ./wdemo-be-0.1.0.tgz` - this upgrade `wdemo-be` application from package
- `helm upgrade -i wdemo-be ./wdemo-be-0.1.0.tgz` - this upgrade `wdemo-be` application with `-i` (install if name doesn't exist)

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
