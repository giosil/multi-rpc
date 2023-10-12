# WDemo - Installation of Elastic Stack ELK: Elasticsearch, Logstash and Kibana.

## Install Elasticsearch

`helm install elasticsearch-dew bitnami/elasticsearch`

Check installation:

`kubectl port-forward --namespace default svc/elasticsearch-dew 9200:9200`

`wget http://localhost:9200/_cat/indices?v=true&s=index`

## Install Kibana

`helm install kibana-dew bitnami/kibana --set elasticsearch.hosts[0]=elasticsearch-dew.default.svc.cluster.local,elasticsearch.port=9200`

Check installation:

`kubectl port-forward --namespace default svc/kibana-dew 8080:5601`

`wget http://localhost:8080`

## Install Logstash

`helm install logstash-dew -f logstash-values.yaml bitnami/logstash`

## Install Filebeat on tomcat POD

`yum install curl`

`yum install tar`

`yum install gzip`

`yum install vim`

`cd /root`

`curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-8.10.3-linux-x86_64.tar.gz`

`tar xzvf filebeat-8.10.3-linux-x86_64.tar.gz`

`cd filebeat-8.10.3-linux-x86_64/`

`vi filebeat.yml`

```yaml
filebeat.inputs:

#...
  paths:
    - /usr/local/tomcat/logs/*.log
#...

#...
setup.kibana:
  host: "kibana-dew.default.svc.cluster.local:5601"
#...

#...
output.elasticsearch:
  # Array of hosts to connect to.
  hosts: ["elasticsearch-dew.default.svc.cluster.local:9200"]
#...

###### OR ######

#...
output.logstash:
  # The Logstash hosts
  hosts: ["logstash-dew.default.svc.cluster.local:5044"]
#...
```

`./filebeat modules enable tomcat`

`vi modules.d/tomcat.yml`

enabled: true

`./filebeat -e`

or

`./filebeat setup -e`

## Uninstall

`helm uninstall logstash-dew`

`helm uninstall kibana-dew`

`helm uninstall elasticsearch-dew`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
