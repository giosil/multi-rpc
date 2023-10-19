# WDemo - Installation of Gitlab

## Install Gitlab with Docker

`docker pull gitlab/gitlab-ee:latest`

`docker run --detach --hostname gitlab.dew.org --publish 443:443 --publish 80:80 --publish 22:22 --name gitlab --shm-size 256m gitlab/gitlab-ee:latest`

## Install Gitlab with Kubernetes

`kubectl apply -f gitlab.yaml`

Add to hosts file:

127.0.0.1	gitlab.dew.org

Please wait several minutes before logging in to `http://gitlab.dew.org`.

Check hostname in gitlabe pod.

## First login

user: root

To get password, connect to container or pod shell and execute:

`cat /etc/gitlab/initial_root_password`

## Install Gitlab runner

`curl -L --output /usr/local/bin/gitlab-runner https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-linux-amd64`

`chmod +x /usr/local/bin/gitlab-runner`

`useradd --comment 'GitLab Runner' --create-home gitlab-runner --shell /bin/bash`

After registration. Example:

`gitlab-runner register --url http://gitlab.dew.org --token glrt-nF6mCExSc-XVhszy_ECs`

Run:

`gitlab-runner run`

`CTRL + \` - to stop gracefully

Run as service:

`gitlab-runner install --user=gitlab-runner --working-directory=/home/gitlab-runner`

`gitlab-runner start`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
